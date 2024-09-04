import { forwardRef, useContext, useEffect, useState } from "react";
import TituloSecao from "../../../components/tituloSecao/tituloSecao";
import UserResult from "../../../components/userResult/userResult";
import styles from "./solicitar.module.css";
import { useLocation, useNavigate } from "react-router-dom";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { UserContext } from "../../../contexts/userContext";
import { criarMonitoria } from "../../../api/api";
import { addHours, addMinutes, format } from "date-fns";

export default function Solicitar() {
    const { user } = useContext(UserContext);

    const navigate = useNavigate();
    const location = useLocation();
    const monitor = location.state.monitor;

    const disciplinas = monitor.disciplinas;

    const [ dataMonitoria, setDataMonitoria ] = useState(addHours(new Date(), 1));
    const [ disciplinaId, setDisciplinaId ] = useState(disciplinas[0].id);
    const [ local, setLocal ] = useState("");

    const SeletorData = forwardRef(
        ({value, onClick, className}, ref) => (
            <button className={className} onClick={onClick} ref={ref} type="button">
                {value}
            </button>
        ),
    );

    const validTime = (time) => {
        const currentDate = new Date();
        const selectedDate = new Date(time);

        return addMinutes(currentDate.getTime(), 30) < selectedDate.getTime();
    }

    const [ erroMensagem, setErroMensagem ] = useState("");

    const solicitarMonitoria = async (ev) => {
        ev.preventDefault();

        if(!validTime(dataMonitoria)) {
            setErroMensagem("Data inválida");
            return;
        }

        const monitoria = {
            data: format(dataMonitoria, 'yyyy-MM-dd HH:mm:ss'),
            local: local,
            monitorId: monitor.id,
            alunoId: user.id,
            disciplinaId: disciplinaId,
        };

        const ok = await criarMonitoria(monitoria);

        if(ok) {
            navigate("/dashboard/monitorias");
        } else {
            setErroMensagem("Falha ao solicitar monitoria");
        }
    };

    return (
        monitor != undefined && (
        <div className={styles.container}>
            <div className={styles.secao}>
            <TituloSecao text="Monitor" />
            <UserResult displayUser={monitor} esconderBotao={true}/>
            </div>
            <div className={styles.secao}>
            <TituloSecao text="Formulário de Solicitação" />
            <form className={styles.formContainer} onSubmit={solicitarMonitoria}>
                <div className={styles.inputContainer}>
                    <label>Data:</label>
                    <DatePicker
                        selected={dataMonitoria}
                        onChange={(data) => {
                            if(validTime(data)) {
                                setDataMonitoria(data);
                            } else {
                                setDataMonitoria(addHours(new Date(), 1));
                            }
                        }}
                        showTimeSelect
                        timeFormat="HH:mm"
                        timeIntervals={15}
                        timeCaption="Horário"
                        dateFormat="dd/MM/yyyy HH:mm"
                        minDate={new Date()}
                        filterTime={validTime}
                        customInput={<SeletorData className={styles.seletorData}/>}
                        required
                    />
                </div>
                <div className={styles.inputContainer}>
                    <label>Local:</label>
                    <input 
                        className={styles.inputBox}
                        value={local}
                        name="local"
                        onChange={(ev) => setLocal(ev.target.value)}
                        required
                    />
                </div>
                <div className={styles.inputContainer}>
                    <label>Disciplina:</label>
                    <select 
                        name="disciplina" 
                        value={disciplinaId.id}
                        className={styles.disciplinaSelect}
                        onChange={(ev) => setDisciplinaId(ev.target.value)}
                        required
                    >
                        {disciplinas.map((disciplina) => (
                            <option value={disciplina.id} key={disciplina.id}>{disciplina.nome}</option>
                        ))}
                    </select>
                </div>
                <button 
                    className={styles.formButton}
                    type="submit"
                >Solicitar</button>
            </form>
            </div>
            <p className={styles.erro}>{erroMensagem}</p>
        </div>
        )
    )
}
