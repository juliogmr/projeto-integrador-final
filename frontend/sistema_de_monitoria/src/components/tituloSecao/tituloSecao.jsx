import styles from "./tituloSecao.module.css";

export default function TituloSecao({ text }) {
    return (
        <div className={styles.container}>
            <h3 className={styles.titulo}>{text}</h3>
            <hr className={styles.linha}></hr>
        </div>
    );
}
