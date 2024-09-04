import { useContext, useEffect } from "react";
import avatar from "../../assets/avatar.png";
import star_empty from "../../assets/star_empty.png";
import star_full from "../../assets/star_full.png";

import styles from "./userResult.module.css";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../contexts/userContext";

export default function UserResult({ displayUser, esconderBotao=false }) {
    const { user } = useContext(UserContext);

    const navigate = useNavigate();


    const avaliacaoEstrelas = [];
    for (let index = 0; index < displayUser.avaliacao; index++) {
        avaliacaoEstrelas.push(star_full);
    }

    for (let index = displayUser.avaliacao; index < 5; index++) {
        avaliacaoEstrelas.push(star_empty);
    }

    let disciplinasList = displayUser.disciplinas.map((disciplina) => {
        return disciplina.nome
    });

    const disciplinas = disciplinasList.join(", ");

    const solicitar = async () => {
        navigate("/dashboard/solicitar", {state: { monitor: displayUser }})
    }


    return (
        <div className={styles.container}>
            <img className={styles.avatar} src={avatar} alt="Avatar" />
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Nome: {displayUser.nome}</h4>
                <div className={styles.avaliacaoContainer}>
                    {avaliacaoEstrelas.map((estrela, index) => (
                        <img key={index} className={styles.estrela} src={estrela} alt="Avaliação" />
                    ))}
                </div>
            </div>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Disciplinas:</h4>
                <p>{disciplinas}</p>
            </div>
            {user.tipoUsuario === "alunos" && !esconderBotao &&
            <button onClick={solicitar} className={styles.solicitarButton}>Solicitar Monitoria</button>
            }
        </div>
    );
}