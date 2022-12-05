import React from 'react';
import styles from "./styles.module.scss";
import { IoIosAdd, IoIosRemove } from 'react-icons/io'

type IconButtonProps = {
	type: string;
}

export default function NormalButton(props: IconButtonProps) {
	const icons_style = {color: "white", fontSize: "3rem"}
	return ( 
		// NORMAL BUTTON CONTAINER
		<div className={styles.login_container}>
			{props.type == 'add' && <IoIosAdd style={icons_style} />}
			{props.type == 'remove' && <IoIosRemove style={icons_style} />}
			
		</div>
	);
}