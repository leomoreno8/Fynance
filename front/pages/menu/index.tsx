import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react'
import Footer from '../../components/Footer'
import styles from '../../styles/Home.module.scss'
import jsCookie from 'js-cookie';
import Router from 'next/router';
import Products from '../../components/Wallets'
import Header from '../../components/Header'
import IconButton from '../../components/IconButton'
import AddWallet from './../../components/AddWallet/index';

const Home: NextPage = () => {

	function loggedFunction() {
		const cookie = jsCookie.get();
		if(!cookie.hasOwnProperty("token")) {
			setLogged(false);
			Router.push('/');
		} else {
			setLogged(true);
		}
	}

	const [logged, setLogged] = useState(false);
	
	const [addWallet, setAddWallet] = useState(false);

	useEffect(() => {
		loggedFunction();
	}, []);

	return (
		<div className={styles.container}>
			<Head>
				<title>Basic Login</title>
				<meta name="description" content="Basic Login App" />
				<link rel="icon" href="/favicon.ico" />
			</Head>

			{logged && (
				<>
					<main className={styles.main}>
						<Header />
						<div className={styles.header_title}>
							<h1>WALLET</h1>
							{!addWallet && 
								<>
									<a onClick={() => setAddWallet(true)}>
										<IconButton type='add'/>
									</a>		
								</>
							}	
							{addWallet && 
								<>
									<a onClick={() => setAddWallet(false)}>
										<IconButton type='add'/>
									</a>		
								</>
							}
													
						</div>
						{addWallet && <AddWallet /> }
						<Products />
					</main>
					<Footer />        
				</>
			)}
		</div>
	)
}

export default Home
