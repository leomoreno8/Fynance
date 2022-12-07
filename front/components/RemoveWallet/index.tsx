import React from 'react';
import styles from "./styles.module.scss";
import Axios from "axios"
import { ToastContainer } from 'react-toastify'
import ToastError from "../../components/ToastError/index"
import Router from 'next/router';
import jsCookie from 'js-cookie';
import ToastSuccess from "../ToastSuccess/index"
import YesNoButton from '../YesNoButton';

type EditWalletProps = {
  id: number;
  name: string;
}

export default function RemoveWallet(props: EditWalletProps) {

  function noRemove() {
    Router.push('/');
  }

  async function yesRemove() {
    console.log(props.id)
    let URL = process.env.NEXT_PUBLIC_APIURL + "/wallets/" + props.id;
    try {
      const removeWallet = await Axios.delete(URL)

      if(removeWallet.data.error) {
        ToastError(removeWallet.data.error);
      } else {
        ToastSuccess("Wallet Removed Successfully");
        Router.push('/');
      }
      
    } catch (error) {
      ToastError("There was an error. Try again.");
    }

  }

  async function handleClickEditWallet(values: { name: string; type: string; balance: number; bank: number; agency: number; number: number; }) {

    let URL = process.env.NEXT_PUBLIC_APIURL + "/wallets/update/" + props.id;

    try {
      const createWallet = await Axios.post(URL, {
        name: values.name,
        type: values.type,
        balance: values.balance,
        bank: values.bank,
        agency: values.agency,
        number: values.number,
        user: {id: jsCookie.get('id')}
      })

      if(createWallet.data.error) {
        ToastError(createWallet.data.error);
      } else {
        ToastSuccess("Wallet Added Successfully");
        Router.push('/menu');
      }
      
    } catch (error) {
      ToastError("There was an error. Try again.");
    }
  }

    return ( 
      <div className={styles.remove_wallet_container}>
        {/* LOGIN BOX  */}
        <div className={styles.modal_box}>
          <h2>Are you sure you want to remove the wallet: {props.name}? </h2>
          <div className={styles.button_box}>
            <a onClick={() => yesRemove()} >
              <YesNoButton type='YES' />
            </a>
            <a onClick={() => noRemove()}>
              <YesNoButton type='NO' />
            </a>
          </div>
        </div>

        <ToastContainer
          position="bottom-center"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="dark"
        />
      </div>
    );
}