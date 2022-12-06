import React, { useEffect, useState } from 'react';
import styles from "./styles.module.scss";
import Axios from "axios"
import IconButton from '../IconButton';
import jsCookie from 'js-cookie';
import EditWallet from '../EditWallet';

export default function Wallets() {

	const [wallets, setWallet] = useState([]);

	const userId = jsCookie.get('id');

	const [componentEditWallet, setComponentEditWallet] = useState({id: 0, name: ''});
	const [componentRemoveWallet, setComponentRemoveWallet] = useState(0);

	async function getWallets() {

    let URL = process.env.NEXT_PUBLIC_APIURL + "/wallets";

    try {
      const walletsList = await Axios.get(URL, {})
			let dataWallets = walletsList.data;
			dataWallets = dataWallets.filter(
				function(user: any) { 
					return user.user.id == userId
				}
			)
			setWallet(dataWallets);      
    } catch (error) {
      console.log("Error in get wallets")
    }
  }

	useEffect(() => {
		getWallets();
		console.log(componentEditWallet)
	}, []);

	return (
		<>
			{componentEditWallet.id > 0 && <EditWallet id={componentEditWallet.id} name={componentEditWallet.name} />}
			<div className={styles.wallets_container}>
				<div className={styles.grid}>
					{wallets.map((wallet : {id: number, name: string, type: string, balance: number, bank: number, agency: number, number: number}) => (
						<div className={styles.item} tabIndex={wallet.id} key={wallet.id}>
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
								{componentEditWallet.id > 0 && componentEditWallet.id != wallet.id &&
									<div onClick={() => setComponentEditWallet({id: wallet.id, name: wallet.name})} className={styles.edit_button}>
										<IconButton type='edit'/>
									</div>
								}
								{componentEditWallet.id == 0 && 
									<div onClick={() => setComponentEditWallet({id: wallet.id, name: wallet.name})} className={styles.edit_button}>
										<IconButton type='edit'/>
									</div>
								}
								{componentEditWallet.id == wallet.id && 
									<div onClick={() => setComponentEditWallet({id: 0, name: ''})} className={styles.edit_button}>
										<IconButton type='edit'/>
									</div>
								}
								<div onClick={() => setComponentRemoveWallet(wallet.id)} className={styles.trash_button}>
									<IconButton type='trash'/>
								</div>
							</div>
						</div>
					))}
				</div>
			</div>
		</>
	);
}