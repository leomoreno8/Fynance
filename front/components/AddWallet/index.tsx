import React from 'react';
import styles from "./styles.module.scss";
import Link from 'next/link'
import ShadowButton from '../ShadowButton';
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as yup from "yup"
import Axios from "axios"
import { ToastContainer } from 'react-toastify'
import ToastError from "../../components/ToastError/index"
import Router from 'next/router';
import jsCookie from 'js-cookie';
import ToastSuccess from "../ToastSuccess/index"


export default function AddWallet() {

  async function handleClickCreateWallet(values: { name: string; type: string; balance: number; bank: number; agency: number; number: number; }) {

    let URL = process.env.NEXT_PUBLIC_APIURL + "/wallets/save";
    console.log(URL)

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

      console.log(createWallet)

      if(createWallet.data.error) {
        ToastError(createWallet.data.error);
      } else {
        ToastSuccess("Wallet Added Successfully");
      }
      
    } catch (error) {
      ToastError("There was an error. Try again.");
    }
  }

  const validationCreateWallet = yup.object().shape({
    name: yup
      .string()
      .min(3, "Invalid Name")
      .required("This field is required"),
    type: yup
      .string()
      .min(3, "Invalid Type")
      .required("This field is required"),
    balance: yup
      .number()
      .min(1, "Invalid Balance")
      .required("This field is required"),
    bank: yup
      .number(),
    agency: yup
      .number(),
    number: yup
      .number(),
  });

    return ( 
      <div className={styles.login_container}>
        {/* LOGIN BOX  */}
        <div className={styles.login_box}>
          <Formik
          initialValues={{'name': '', 'type': '', 'balance': '', 'bank': '', 'agency': '', 'number': ''}}
          onSubmit={handleClickCreateWallet}
          validationSchema={validationCreateWallet}
          >
            <Form className={styles.inputs_container}>
              <div className={styles.first_line}>
                <div>
                  <Field name="name" placeholder="Name" />
                  <ErrorMessage 
                    component="span"
                    name="name"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <Field name="type" placeholder="Type" />
                  <ErrorMessage 
                    component="span"
                    name="type"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <Field name="balance" type="number" placeholder="Balance" />
                  <ErrorMessage 
                    component="span"
                    name="balance"
                    className={styles.form_error}
                  />
                </div>
              </div>

              <div className={styles.second_line}>
                <div>
                  <Field name="bank" type="number" placeholder="Bank" />
                  <ErrorMessage 
                    component="span"
                    name="bank"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <Field name="agency" type="number" placeholder="Agency" />
                  <ErrorMessage 
                    component="span"
                    name="agency"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <Field name="number" type="number" placeholder="Number" />
                  <ErrorMessage 
                    component="span"
                    name="number"
                    className={styles.form_error}
                  />
                </div>
              </div>            

              <div className={styles.button_login_box}> 
                <button type="submit">
                  <ShadowButton text='CREATE'/>
                </button>
              </div>
            </Form>
          </Formik>
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