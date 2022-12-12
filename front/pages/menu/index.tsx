import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react'
import Footer from '../../components/Footer'
import styles from '../../styles/Home.module.scss'
import jsCookie from 'js-cookie';
import Router from 'next/router';
import Wallets from '../../components/Wallets'
import Header from '../../components/Header'
import IconButton from '../../components/IconButton'
import AddWallet from './../../components/AddWallet/index';

const Menu: NextPage = () => {

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
				<title>Fynance</title>
				<meta name="description" content="Fynance App" />
				<link rel="icon" href="/favicon.ico" />
			</Head>

			{logged && (
				<>
					<main className={styles.main}>
						<Header page='menu'/>
						<div className={styles.header_title}>
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
							<h1>WALLETS</h1>
						</div>
						<div className={styles.page_content}>
							{addWallet && <AddWallet /> }
							<Wallets />
						</div>
					</main>
					<Footer />        
				</>
			)}
		</div>
	)
}

export default Menu
