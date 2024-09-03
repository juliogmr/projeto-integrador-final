import { useContext } from "react";
import { UserContext } from "../../../contexts/userContext";
import Monitorias from "../monitorias/monitorias";
import styles from "./summary.module.css";

export default function Summary() {
    const { user } = useContext(UserContext);

    return (
        <div className={styles.container}>
            <Monitorias title="Próxima Monitoria" limite={1} embedded={true} />
        </div>
    );
}