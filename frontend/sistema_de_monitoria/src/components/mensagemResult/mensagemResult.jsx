import { useState } from "react";
import { aceitarMonitoria, deletarMensagem, recusarMonitoria } from "../../api/api";
import styles from "./mensagemResult.module.css";


export default function MensagemResult({ mensagem }) {
    const [ erroResposta, setErroResposta ] = useState("");
    const [ sucessoResposta, setSucessoResposta ] = useState("");
    const [ resolvido, setResolvido ] = useState(false);

    const aceitar = async () => {
        const resultado = await aceitarMonitoria(mensagem.monitoria);

        if(!resultado) {
            setErroResposta("Falha ao aceitar a monitoria");
        } else {
            setSucessoResposta("Monitoria aceita com sucesso");
        }
        
    }

    const recusar = async () => {
        const resultado = await recusarMonitoria(mensagem.monitoria);

        if(!resultado) {
            setErroResposta("Falha ao recusar a monitoria");
        } else {
            setSucessoResposta("Monitoria recusada com sucesso");
            setResolvido(true);
        }
    }

    const deletar = async () => {
        const resultado = await deletarMensagem(mensagem);

        if(!resultado) {
            setErroResposta("Falha ao deletar mensagem");
        } else {
            setSucessoResposta("Mensagem excluída com sucesso");
            setResolvido(true);
        }
    }

    return (
        <div className={`${styles.container}`}>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>{mensagem.data.toLocaleString()}</h4>
                <p><span className={styles.bold}>Aluno:</span> {mensagem.monitoria.aluno.nome}</p>
            </div>
            <div className={styles.col}>
                <h4 className={styles.colTitulo}>Local: {mensagem.monitoria.local}</h4>
                <p><span className={styles.bold}>Disciplina:</span> {mensagem.monitoria.disciplina.nome}</p>
            </div>
            <div className={styles.col}>
                <p><span className={styles.bold}>{mensagem.tipo}</span></p>
                {!resolvido &&
                    <>
                    {mensagem.tipo === "Solicitação de Monitoria" ?
                        <>
                        <button onClick={aceitar}>Aceitar</button>
                        <button onClick={recusar}>Recusar</button>
                        </>
                    :  
                    <button onClick={deletar}>Deletar</button>
                    }
                    </>
                }

                {resolvido &&
                    <>
                        <p className={styles.erro}>{erroResposta}</p>
                        <p className={styles.sucesso}>{sucessoResposta}</p>
                    </>
                }
            </div>
        </div>
    );
}