import { useContext, useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { UserContext } from "../contexts/userContext";
import styles from "./dashboard.module.css";
import NavButton from "./navButton";
import DividingLine from "./dividingLine";


export default function Dashboard() {
    const { user, setUser } = useContext(UserContext);
    const navigate = useNavigate();

    let buttons = [
        {
            text: "Dashboard", dest: "/dashboard", tipos: ["alunos", "monitores", "instituicoes"],
        },
        {
            text: "Encontrar Alunos", dest: "/dashboard/encontrar", tipos: ["monitores", "instituicoes"],
        },
        {
            text: "Encontrar Monitores", dest: "/dashboard/encontrar", tipos: ["alunos", "instituicoes"],
        },
        {
            text: "Monitorias Agendadas", dest: "/dashboard/monitorias", tipos: ["alunos"],
        },
        {
            text: "Minhas Monitorias", dest: "/dashboard/monitorias", tipos: ["monitores"],
        },
        {
            text: "Mensagens", dest: "/dashboard/mensagens", tipos: ["alunos", "monitores", "instituicoes"],
        },
    ];

    useEffect(() => {
        if (!user) {
            navigate("/")
        }
    }, [])
    
    const logout = () => {
        setUser(null);
        navigate("/", {state: {selected: user.tipoUsuario}});
    };

    return (
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <h2>Dashboard {`
                ${user.tipoUsuario === "alunos" ? "do Aluno"
                    : user.tipoUsuario === "monitores" ? "do Monitor"
                    : user.tipoUsuario === "instituicoes" ? "da Instituição"
                    : ""}
                `}</h2>
                {user && <>
                <h3>{user.nome}</h3>
                <ul>
                    {buttons.filter((button) => button.tipos.includes(user.tipoUsuario)).map((button) => (
                    <li key={button.text}>
                        <NavButton text={button.text} dest={button.dest} />
                    </li>
                    ))}
                </ul>
                <button type="button" onClick={logout} id={styles.logoutButton}>Sair</button>
                </>}
            </nav>
            <main className={styles.mainContent}>
                    <Outlet />
            </main>
        </div>
    )
}