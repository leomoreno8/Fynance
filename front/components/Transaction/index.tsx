import React, { useEffect, useState } from 'react';
import styles from "./styles.module.scss";
import Axios from "axios"
import IconButton from '../IconButton';
import jsCookie from 'js-cookie';
import EditWallet from '../EditWallet';
import RemoveWallet from '../RemoveWallet';
import Router, {useRouter} from 'next/router';

export default function Transactions() {

	const router = useRouter()
	const { id } = router.query
	const idWallet = parseInt({id}.id);
	
	const [transactions, setTransactions] = useState([]);

	const userId = jsCookie.get('id');

	const [componentEditWallet, setComponentEditWallet] = useState({id: 0, name: ''});
	const [componentRemoveWallet, setComponentRemoveWallet] = useState({id: 0, name: ''});

	function enterWallet(id: number, name: string) {
		Router.push('/wallet/' + id + '/' + name);
	}

	async function getTransactions() {

    let URL = process.env.NEXT_PUBLIC_APIURL + "/transactions";

    try {
      const transactionsList = await Axios.get(URL, {})
			let dataTransactions = transactionsList.data;
			dataTransactions = dataTransactions.filter(
				function(wallet: any) { 
					return wallet.wallet.id == idWallet
				}
			)
			setTransactions(dataTransactions);      
    } catch (error) {
      console.log("Error in get wallets")
    }
  }

	useEffect(() => {
		getTransactions();
	}, []);

	return (
		<>
			

			{componentEditWallet.id > 0 && <EditWallet id={componentEditWallet.id} name={componentEditWallet.name} />}
			<div className={styles.transactions_container}>
				<div className={styles.grid}>
					{transactions.map((transaction : {id: number; name: string; dateTransaction: string; valueTransaction: number; es: string; description: string; category: string; fees: number; total: number;}) => (
						<div className={styles.item} tabIndex={transaction.id} key={transaction.id}>
							<div className={styles.box}>
								<div className={styles.text}><span>{transaction.name}</span></div>
								<span className={styles.type}>{transaction.category}</span>
								<span className={styles.type}>{transaction.dateTransaction}</span>
								{transaction.es == 'E' && 
									<span className={styles.valueE}>+R${transaction.total}</span>
								}
								{transaction.es == 'S' && 
									<span className={styles.valueS}>-R${transaction.total}</span>
								}

								<div className={styles.tooltip}>
									<div className={styles.subtext}>
										<span>Fee: {transaction.fees}</span>
										<span>Category: {transaction.category}</span>
										<span>Description: {transaction.description}</span>
									</div>
								</div>

								{componentRemoveWallet.id > 0 && componentRemoveWallet.id == transaction.id &&
									<div className={styles.remove_modal}>
										<RemoveWallet id={componentRemoveWallet.id} name={componentRemoveWallet.name} />
									</div>
								}

								{componentEditWallet.id > 0 && componentEditWallet.id != transaction.id &&
									<div onClick={() => setComponentEditWallet({id: transaction.id, name: transaction.name})} className={styles.edit_button}>
										<IconButton type='edit'/>
									</div>
								}
								{componentEditWallet.id == 0 && 
									<div onClick={() => setComponentEditWallet({id: transaction.id, name: transaction.name})} className={styles.edit_button}>
										<IconButton type='edit'/>
									</div>
								}
								{componentEditWallet.id == transaction.id && 
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
									<div onClick={() => setComponentRemoveWallet({id: transaction.id, name: transaction.name})} className={styles.trash_button}>
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