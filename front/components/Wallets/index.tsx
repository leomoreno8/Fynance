import React, { useEffect, useState } from 'react';
import styles from "./styles.module.scss";
import Axios from "axios"
import IconButton from '../IconButton';
import jsCookie from 'js-cookie';
import EditWallet from '../EditWallet';
import RemoveWallet from '../RemoveWallet';
import Router from 'next/router';


export default function Wallets() {

	const [wallets, setWallet] = useState([]);

	const userId = jsCookie.get('id');

	const [componentEditWallet, setComponentEditWallet] = useState({id: 0, name: ''});
	const [componentRemoveWallet, setComponentRemoveWallet] = useState({id: 0, name: ''});

	function enterWallet(id: number, name: string) {
		Router.push('/wallet/' + id + '/' + name);
	}

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
						<div className={styles.item} tabIndex={wallet.id} key={wallet.id} onClick={() => enterWallet(wallet.id, wallet.name)}>
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

								{componentRemoveWallet.id > 0 && componentRemoveWallet.id == wallet.id &&
									<div className={styles.remove_modal}>
										<RemoveWallet id={componentRemoveWallet.id} name={componentRemoveWallet.name} />
									</div>
								}

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
								{componentRemoveWallet.id > 0 && 
									<div onClick={() => setComponentRemoveWallet({id: 0, name: ''})} className={styles.trash_button}>
										<IconButton type='trash'/>
									</div>
								}
								{componentRemoveWallet.id == 0 && 
									<div onClick={() => setComponentRemoveWallet({id: wallet.id, name: wallet.name})} className={styles.trash_button}>
										<IconButton type='trash'/>
									</div>
								}
							</div>
						</div>
					))}
				</div>
			</div>
		</>
	);
}