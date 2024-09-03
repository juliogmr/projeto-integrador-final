import TituloSecao from "../../../components/tituloSecao/tituloSecao";
import styles from "./mensagens.module.css";

export default function Mensagens({ embedded = false }) {

    return (
        <div className={`${styles.container} ${embedded ? styles.embedded : ""}`}>
            <div className={styles.secao}>
                <TituloSecao text="Mensagens" />
                <p>Você não tem novas mensagens</p>
            </div>
        </div>
    )
}