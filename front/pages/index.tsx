import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react'
import Footer from '../components/Footer'
import Login from '../components/Login'
import styles from '../styles/Home.module.scss'
import jsCookie from 'js-cookie';
import Router from 'next/router';

const Home: NextPage = () => {

	const [logged, setLogged] = useState(false);

  function loggedFunction() {
		const cookie = jsCookie.get();
		if(!cookie.hasOwnProperty("token")) {
			setLogged(false);
			Router.push('/');
		} else {
			setLogged(true);
			Router.push('/menu');
		}
	}

  useEffect(() => {
		loggedFunction();
	}, []);

  return (
    <div className={styles.container}>
      <Head>
        <title>Fynance</title>
        <meta name="description" content="Fynance App" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <h1>LOGIN</h1>
        <Login />
      </main>
      <Footer />
    </div>
  )
}

export default Home
