import { Link } from "react-router-dom";
import styles from "./navButton.module.css";


export default function NavButton({ text, dest }) {

    return (
        <Link to={dest}>
            <button type="button" className={styles.button}>
                {text}
            </button>
        </Link>
    );
}