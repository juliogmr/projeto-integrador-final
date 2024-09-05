import { useContext } from "react";
import styles from "./monitoriaResult.module.css";
import { UserContext } from "../../contexts/userContext";
import { monitoriaVencida } from "../../api/api";

export default function MonitoriaResult({ monitoria }) {
    const { user } = useContext(UserContext);

    let tipoParceiro = "";
    let parceiro = "";
    if (user.tipoUsuario === "monitores") {
        tipoParceiro = "Aluno";
        parceiro = monitoria.aluno;
    } else if (user.tipoUsuario === "alunos") {
        tipoParceiro = "Monitor";
        parceiro = monitoria.monitor;
    }

    console.log(monitoria.data);

    const vencida = monitoriaVencida(monitoria);

    return (
        <div
            className={`${styles.container} ${vencida ? styles.vencida : ""} 
            ${
                monitoria.status === "pendente"
                    ? styles.pendente
                    : monitoria.status === "cancelada"
                    ? styles.cancelada
                    : ""
            }`}
        >
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>
                    {monitoria.data.toLocaleString()}
                </h4>
                <p>
                    <span className={styles.bold}>{tipoParceiro}:</span>{" "}
                    {parceiro.nome}
                </p>
            </div>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Local: {monitoria.local}</h4>
                <p>
                    <span className={styles.bold}>Disciplina:</span>{" "}
                    {monitoria.disciplina.nome}
                </p>
            </div>
            <div className={styles.col}>
                <p>
                    <span className={styles.bold}>Status:</span>{" "}
                    {monitoria.status}
                </p>
            </div>
        </div>
    );
}
