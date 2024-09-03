import styles from "./userResult.module.css";
import avatar from "../assets/avatar.png";
import star_empty from "../assets/star_empty.png";
import star_full from "../assets/star_full.png";
import { useEffect } from "react";


export default function UserResult({ user }) {

    const avaliacaoEstrelas = [];
    for (let index = 0; index < user.avaliacao; index++) {
        avaliacaoEstrelas.push(star_full);
    }

    for (let index = user.avaliacao; index < 5; index++) {
        avaliacaoEstrelas.push(star_empty);
    }

    let disciplinasList = user.disciplinas.map((disciplina) => {
        return disciplina.nome
    });

    const disciplinas = disciplinasList.join(", ");

    return (
        <div className={styles.container}>
            <img className={styles.avatar} src={avatar} alt="Avatar" />
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Nome: {user.nome}</h4>
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
        </div>
    );
}