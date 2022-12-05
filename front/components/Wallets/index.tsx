import React, { useEffect, useState } from 'react';
import styles from "./styles.module.scss";
import Axios from "axios"

export default function Wallets() {

	const [wallets, setWallet] = useState([]);

	async function getWallets() {

    let URL = process.env.NEXT_PUBLIC_APIURL + "/wallets";

    try {
      const walletsList = await Axios.get(URL, {})
			let dataWallets = walletsList.data;
			setWallet(dataWallets);      
    } catch (error) {
      console.log("Error in get wallets")
    }
  }

	useEffect(() => {
		getWallets();
	}, []);

    return (
			<>
				<div className={styles.container}>
					<div className={styles.grid}>

						{wallets.map((wallet : {id: number, name: string, type: string, balance: number, bank: number, agency: number, number: number}) => (
							<div className={styles.item} tabIndex={1} key={wallet.id}>
								<div className={styles.box}>
									<div className={styles.text}><span>{wallet.name}</span></div>
									<span className={styles.type}>{wallet.type}</span>
									<span className={styles.type}>R${wallet.balance}</span>

									<div className={styles.tooltip}>
										<div className={styles.subtext}>
											<span>Bank: {wallet.bank}</span>
											<span>Agency: {wallet.agency}</span>
											<span>Number: {wallet.number}</span>
										</div>
									</div>
								</div>
							</div>
						))}
					</div>
				</div>
			</>
    );
}