import { useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import styles from "./encontrar.module.css";
import { UserContext } from "../../../contexts/userContext";


export default function Encontrar() {
    const { user } = useContext(UserContext);
    const location = useLocation();

    const pathParts = location.pathname.split("/");
    const targetString = pathParts[pathParts.length - 1];

    const [ nome, setNome ] = useState("");
    const [ disciplina, setDisciplina ] = useState("");
    const [ target, setTarget ] = useState(targetString);

    useEffect(() => {
        
    });

    return (
        <div className={styles.container}>
            <h2>Encontrar Section</h2>
            <div className={styles.formContainer}>
                <div className={styles.inputContainer}>
                    <label>Nome:</label>
                    <input 
                        className={styles.inputBox}
                        value={nome}
                        name="nome"
                        onChange={(ev) => setNome(ev.target.value)}
                    />
                </div>
                <div className={styles.inputContainer}>
                    <label>Disciplina:</label>
                    <input 
                        className={styles.inputBox}
                        value={disciplina}
                        name="disciplina"
                        onChange={(ev) => setDisciplina(ev.target.value)}
                    />
                </div>
                <button 
                    className={styles.formButton}
                    onClick={() => { buscar(target, nome, disciplina)}}
                >Buscar</button>
            </div>
            <p>Buscando por {target}</p>
        </div>
    )
}