import { useContext, useEffect, useState } from "react";
import styles from "./monitorias.module.css";
import { UserContext } from "../../../contexts/userContext";
import TituloSecao from "../../../components/tituloSecao/tituloSecao";
import { useLocation } from "react-router-dom";
import { buscarMonitorias } from "../../../api/api";
import MonitoriaResult from "../../../components/monitoriaResult/monitoriaResult";


export default function Monitorias({ title = null, limite = null, embedded = false}) {
    const location = useLocation();
    const { user } = useContext(UserContext);
    const [ monitorias, setMonitorias ] = useState([]);
    
    let titulo = "";
    if (title === null) {
        if (user.tipoUsuario === "alunos") {
            titulo = "Monitorias Agendadas";
        } else if(user.tipoUsuario === "monitores") {
            titulo = "Minhas Monitorias";
        }
    } else {
        titulo = title;
    }


    useEffect(() => {
        async function fetchData() {
            const result = await buscarMonitorias(user);

            if(!limite) {
                limite = result.length;
            }

            setMonitorias(result.slice(0, limite));
        };
        fetchData();
    }, [location]);

    return (
        <div className={`${styles.container} ${embedded ? styles.embedded : ""}`}>
            <div className={styles.secao}>
            <TituloSecao text={titulo} />
            {monitorias.length === 0 ?
                <p>Nenhuma monitoria agendada</p>
            : monitorias.map((monitoria, index) => (
                <MonitoriaResult monitoria={monitoria} key={index}/>
            ))}
            </div>
        </div>
    )
}