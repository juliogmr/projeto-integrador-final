import { useState } from "react";
import {
    aceitarMonitoria,
    deletarMensagem,
    recusarMonitoria,
} from "../../api/api";
import styles from "./mensagemResult.module.css";
import MonitoriaResult from "../monitoriaResult/monitoriaResult";
import { useNavigate } from "react-router-dom";
import { waitAndRun } from "../../api/utils";

export default function MensagemResult({ mensagem }) {
    const navigate = useNavigate();

    const [erroResposta, setErroResposta] = useState("");
    const [sucessoResposta, setSucessoResposta] = useState("");
    const [resolvido, setResolvido] = useState(false);

    const aceitar = async () => {
        const resultado = await aceitarMonitoria(mensagem.monitoria);

        if (!resultado) {
            setErroResposta("Falha ao aceitar a monitoria");
        } else {
            setSucessoResposta("Monitoria aceita com sucesso");
            setResolvido(true);

            mensagem.monitoria.status = "confirmada";

            waitAndRun(1500, () => navigate(0));
        }
    };

    const recusar = async () => {
        const resultado = await recusarMonitoria(mensagem.monitoria);

        if (!resultado) {
            setErroResposta("Falha ao recusar a monitoria");
        } else {
            setSucessoResposta("Monitoria recusada com sucesso");
            setResolvido(true);

            mensagem.monitoria.status = "recusada";

            waitAndRun(1500, () => navigate(0));
        }
    };

    const deletar = async () => {
        const resultado = await deletarMensagem(mensagem);

        if (!resultado) {
            setErroResposta("Falha ao deletar mensagem");
        } else {
            setSucessoResposta("Mensagem excluída com sucesso");
            setResolvido(true);

            waitAndRun(1500, () => navigate(0));
        }
    };

    return (
        <div className={`${styles.container}`}>
            <h3 className={styles.titulo}>{mensagem.tipo}</h3>
            <MonitoriaResult monitoria={mensagem.monitoria} />
            <div className={styles.containerBotoes}>
                {resolvido && (
                    <>
                        <p className={styles.erro}>{erroResposta}</p>
                        <p className={styles.sucesso}>{sucessoResposta}</p>
                    </>
                )}
                {!resolvido && (
                    <>
                        {mensagem.tipo === "Solicitação de Monitoria" ? (
                            <>
                                <button
                                    onClick={aceitar}
                                    className={`${styles.respostaBotao} ${styles.aceitar}`}
                                >
                                    Aceitar
                                </button>

                                <button
                                    onClick={recusar}
                                    className={`${styles.respostaBotao} ${styles.recusar}`}
                                >
                                    Recusar
                                </button>
                            </>
                        ) : (
                            <button
                                onClick={deletar}
                                className={`${styles.respostaBotao} ${styles.recusar}`}
                            >
                                Deletar
                            </button>
                        )}
                    </>
                )}
            </div>
        </div>
    );
}
