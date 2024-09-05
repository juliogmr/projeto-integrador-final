import { Outlet } from "react-router-dom";
import styles from "./root.module.css";

export default function Root() {
    return (
        <div className={styles.root}>
            <header className={styles.banner}>
                <div className={styles.bannerText}>Sistema de Monitoria</div>
            </header>
            <Outlet />
        </div>
    );
}
