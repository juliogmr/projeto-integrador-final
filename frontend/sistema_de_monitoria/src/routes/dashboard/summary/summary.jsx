import { useContext } from "react";
import { UserContext } from "../../../contexts/userContext";
import Monitorias from "../monitorias/monitorias";
import styles from "./summary.module.css";
import Mensagens from "../mensagens/mensagens";

export default function Summary() {
    const { user } = useContext(UserContext);

    return (
        <div className={styles.container}>
            {user.tipoUsuario != "instituicoes" && 
                <Monitorias 
                    title="Próxima Monitoria" 
                    limite={1} 
                    embedded={true} 
                    somenteNovas={true}
                />
            }
            <Mensagens embedded={true} />
        </div>
    );
}