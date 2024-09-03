import { useContext } from "react";
import styles from "./monitoriaResult.module.css";
import { UserContext } from "../../contexts/userContext";
import { monitoriaVencida } from "../../api/api";


export default function MonitoriaResult({ monitoria }) {
    let tipoParceiro = "";
    let parceiro = "";
    if (monitoria.aluno != undefined) {
        tipoParceiro = "Aluno";
        parceiro = monitoria.aluno;
    } else if (monitoria.monitor != undefined) {
        tipoParceiro = "Monitor";
        parceiro = monitoria.monitor;
    }

    const vencida = monitoriaVencida(monitoria);

    return (
        <div className={`${styles.container} ${vencida ? styles.vencida : ""}`}>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>{monitoria.data.toLocaleString()}</h4>
                <p><span className={styles.bold}>{tipoParceiro}:</span> {parceiro}</p>
            </div>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Local: {monitoria.local}</h4>
                <p><span className={styles.bold}>Disciplina:</span> {monitoria.disciplina}</p>
            </div>
        </div>
    );
}