import React from 'react';
import styles from "./styles.module.scss";
import Link from 'next/link'
import ShadowButton from '../ShadowButton';
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as yup from "yup"
import Axios from "axios"
import { ToastContainer } from 'react-toastify'
import ToastError from "../../components/ToastError/index"
import Router, {useRouter} from 'next/router';
import jsCookie from 'js-cookie';
import ToastSuccess from "../ToastSuccess/index"
import { number } from 'yup/lib/locale';


export default function AddTransaction() {

  const router = useRouter()
	const { id } = router.query
  const date = new Date();

  const idWallet = parseInt({id}.id);


	
  async function handleClickCreateWallet(values: { name: string; wallet: object; dateTransaction: Date; valueTransaction: number; es: string; description: string; category: string; fees: number; totalTransaction: number; }) {
    let URL = process.env.NEXT_PUBLIC_APIURL + "/transactions/save";

    try {
      const createTransaction = await Axios.post(URL, {
        name: values.name,
        dateTransaction: values.dateTransaction,
        valueTransaction: values.valueTransaction,
        es: values.es,
        description: values.description,
        category: values.category,
        fees: values.fees,
        total: values.totalTransaction,
        wallet: {id: idWallet}
      })


      if(createTransaction.data.error) {
        ToastError(createTransaction.data.error);
      } else {
        ToastSuccess("Transacation Added Successfully");
        Router.push('/menu');
      }
      
    } catch (error) {
      ToastError("There was an error. Try again.");
    }
  }

  const validationCreateTransaction = yup.object().shape({
    name: yup
      .string()
      .min(3, "Invalid Name")
      .required("This field is required"),
    dateTransaction: yup
      .string()
      .min(3, "Invalid Date")
      .required("This field is required"),
    valueTransaction: yup
      .number()
      .min(1, "Invalid Value")
      .required("This field is required"),
    es: yup
      .string()
      .required("This field is required"),
    description: yup
      .string(),
    category: yup
      .string(),
    fees: yup
      .number(),
    totalTransaction: yup
      .number()
      .required("This field is required"),
  });

    return ( 
      <div className={styles.add_wallet_container}>
        {/* LOGIN BOX  */}
        <div className={styles.login_box}>
          <Formik
            // initialValues={{'name': '', 'type': '', 'balance': '', 'bank': '', 'agency': '', 'number': ''}}
            onSubmit={handleClickCreateWallet}
            validationSchema={validationCreateTransaction} initialValues={{
              name: '',
              dateTransaction: date,
              valueTransaction: 0,
              es: '',
              description: '',
              category: '',
              fees: 0,
              totalTransaction: 0
            }}          >
            <Form className={styles.inputs_container}>
              <div className={styles.first_line}>
                <div>
                  <label>Name</label>
                  <Field name="name" placeholder="Name" />
                  <ErrorMessage 
                    component="span"
                    name="name"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Description</label>
                  <Field name="description" placeholder="Description" />
                  <ErrorMessage 
                    component="span"
                    name="description"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Category</label>
                  <Field name="cateogry" placeholder="Category" />
                  <ErrorMessage 
                    component="span"
                    name="cateogry"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Date</label>
                  <Field name="dateTransaction" placeholder="Date" />
                  <ErrorMessage 
                    component="span"
                    name="dateTransaction"
                    className={styles.form_error}
                  />
                </div>

              </div>

              <div className={styles.second_line}>
                <div>
                  <label>Value</label>
                  <Field name="valueTransaction" type="number" placeholder="0" />
                  <ErrorMessage 
                    component="span"
                    name="valueTransaction"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Entrada(E) / Saída (S)</label>
                  <Field name="es" placeholder="Entrada(E) / Saída (S)" />
                  <ErrorMessage 
                    component="span"
                    name="es"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Fees</label>
                  <Field name="fees" type="number" placeholder="Fees" />
                  <ErrorMessage 
                    component="span"
                    name="fees"
                    className={styles.form_error}
                  />
                </div>

                <div>
                  <label>Total</label>
                  <Field name="totalTransaction" type="number" placeholder="Total" />
                  <ErrorMessage 
                    component="span"
                    name="totalTransaction"
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