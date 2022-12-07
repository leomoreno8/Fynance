import React from 'react';
import styles from "./styles.module.scss";

type YesNoButtonProps = {
	type: string;
}

export default function YesNoButton(props: YesNoButtonProps) {
	return ( 
		// YES/NO BUTTON CONTAINER
		<div className={styles.login_container}>
            {props.type}
		</div>
	);
}