import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./loginForm.module.css";
import { fazerLogin } from "../api/login";
import { UserContext } from "../contexts/userContext";

export default function LoginForm({ tipoUsuario }) {
  const { setUser } = useContext(UserContext);

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [formError, setFormError] = useState('');

  const navigate = useNavigate();

  const onButtonClick = async () => {
    setFormError("");

    if(email === "") {
        setFormError("Campo email é obrigatório");
        return;
    }

    if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
        setFormError("Email inválido");
        return;
    }

    if ('' === password) {
        setFormError("Campo senha é obrigatório");
        return;
    }
    
    const user = await fazerLogin(tipoUsuario, email, password);

    if (user) {
        setUser(user);
        navigate("/dashboard");
    }
    else {
        setFormError("Usuário ou senha inválidos");
    }
  }

  return (
      <div className={styles.formContainer}>
        <div className={styles.inputContainer}>
            <label htmlFor="email">Email:</label>
            <input 
                name="email"
                value={email}
                type="email"
                placeholder="Digite o seu email"
                onChange={(ev) => setEmail(ev.target.value)}
                className={styles.inputBox}
                required
            />
        </div>
        <div className={styles.inputContainer}>
            <label htmlFor="password">Senha:</label>
            <input
                name="password"
                value={password}
                type="password"
                placeholder="Digite a sua senha"
                onChange={(ev) => setPassword(ev.target.value)}
                className={styles.inputBox}
                required
            />
        </div>
        <label className={styles.errorLabel}>{formError}</label>
        <button type="submit" className={styles.formButton} onClick={onButtonClick}>Login</button>
      </div>
  );
}
