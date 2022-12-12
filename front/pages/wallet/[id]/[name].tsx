import type { NextPage } from 'next'
import Head from 'next/head'
import { useEffect, useState } from 'react'
import Footer from '../../../components/Footer'
import styles from '../../../styles/Home.module.scss'
import jsCookie from 'js-cookie';
import Router, {useRouter} from 'next/router';
import Wallets from '../../../components/Wallets'
import Header from '../../../components/Header'
import IconButton from '../../../components/IconButton'
import AddWallet from '../../../components/AddWallet/index';
import Transactions from '../../../components/Transaction'
import AddTransaction from '../../../components/AddTransaction'


const Wallet: NextPage = () => {

	const router = useRouter()
	const { id } = router.query
	const { name } = router.query


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
	
	const [addTransaction, setAddTransaction] = useState(false);

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
						<Header page='wallet' />
						<div className={styles.header_title}>
							{!addTransaction && 
								<>
									<a onClick={() => setAddTransaction(true)}>
										<IconButton type='add'/>
									</a>		
								</>
							}	
							{addTransaction && 
								<>
									<a onClick={() => setAddTransaction(false)}>
										<IconButton type='add'/>
									</a>		
								</>
							}						
							<h1>{name}</h1>

						</div>
						<div className={styles.page_content}>
							{addTransaction && <AddTransaction /> }
							{/* <Wallets0 /> */}
							<Transactions />
						</div>
					</main>
					<Footer />        
				</>
			)}
		</div>
	)
}

export default Wallet
