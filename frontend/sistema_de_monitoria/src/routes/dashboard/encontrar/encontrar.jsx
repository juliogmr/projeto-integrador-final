import { useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import styles from "./encontrar.module.css";
import { UserContext } from "../../../contexts/userContext";
import TituloSecao from "../../../components/tituloSecao/tituloSecao";
import UserResult from "../../../components/userResult/userResult";
import { buscarUsuarios } from "../../../api/api";


export default function Encontrar() {
    const { user } = useContext(UserContext);
    const location = useLocation();


    const [ nome, setNome ] = useState("");
    const [ disciplina, setDisciplina ] = useState("");
    const [ target, setTarget ] = useState("alunos");

    const [ resultados, setResultados ] = useState([]);

    useEffect(() => {
        const pathParts = location.pathname.split("/");
        const targetString = pathParts[pathParts.length - 1];

        setTarget(targetString);

        setResultados([]);
    }, [location]);

    const buscar = async (target, nome, disciplina) => {
        setResultados(await buscarUsuarios(target, nome, disciplina));
    }

    return (
        <div className={styles.container}>
            <div className={styles.secao}>
            <TituloSecao text={`Encontrar ${target[0].toUpperCase()}${target.slice(1)}`} />
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
            </div>
            <div className={styles.secao}>
            <TituloSecao text="Resultados" />
            {resultados.length > 0 && resultados.map((user) => (
                <UserResult user={user} key={user.id}/>
            ))}
            </div>
        </div>
    )
}