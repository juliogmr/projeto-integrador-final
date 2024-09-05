import { useContext, useEffect, useState } from "react";
import TituloSecao from "../../../components/tituloSecao/tituloSecao";
import styles from "./mensagens.module.css";
import { UserContext } from "../../../contexts/userContext";
import { buscarMensagens } from "../../../api/api";
import MensagemResult from "../../../components/mensagemResult/mensagemResult";

export default function Mensagens({ embedded = false }) {
    const { user } = useContext(UserContext);

    const [mensagens, setMensagens] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            let mensagens = await buscarMensagens(user);

            mensagens = mensagens.filter(
                (mensagem) => mensagem.respondida === false
            );

            setMensagens(mensagens);
        };

        fetchData();
    }, []);

    return (
        <div
            className={`${styles.container} ${embedded ? styles.embedded : ""}`}
        >
            <div className={styles.secao}>
                <TituloSecao text="Mensagens" />
                {mensagens.length === 0 ? (
                    <p>Você não tem novas mensagens</p>
                ) : (
                    mensagens.map((mensagem) => {
                        return (
                            <MensagemResult
                                mensagem={mensagem}
                                key={mensagem.id}
                            />
                        );
                    })
                )}
            </div>
        </div>
    );
}
