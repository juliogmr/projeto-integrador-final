import LoginForm from '../components/loginForm';
import styles from './root.module.css'
import { useState } from 'react';

export default function Root() {

    const [selectedTab, setSelectedTab] = useState("alunos");

    const titles = {
        "alunos": "Tirar aquela dúvida ficou ainda mais fácil!",
        "monitores": "Crie sua própria rede de alunos!",
        "instituicoes": "Acompanhe o progresso de seus monitores!",
    };

    const descriptions = {
        "alunos": (
        <>
            <p>Encontre monitores qualificados e prestativos, prontos para lhe ajudar a tirar aquela dúvida ou entender aquele conceito.</p>
            <ul>
                <li>Procure pelos monitores que melhor se encaixam com as suas necessidades.</li>
                <li>Marque mentorias online ou presenciais no local e horário que preferir.</li>
                <li>Troque mensagens para aquelas dúvidas rápidas ou confirmações.</li>
            </ul>
        </>),

        "monitores": (
        <>
            <p>Monte seu perfil, escolha suas habilidades, e receba o contato de alunos interessados. Ensinar é aprender, reforce conceitos por meio da monitoria voluntária.</p>
            <ul>
                <li>Mantenha um contato direto com seus alunos por meio de nossa plataforma.</li>
                <li>Escolha o local e o horário das suas mentorias.</li>
                <li>Tenha acesso a materiais de apoio disponibilizados por instituições reconhecidas.</li>
            </ul>
        </>
        ),

        "instituicoes": (
        <>
            <p>Receba relatórios e estatísticas sobre as atividades dos monitores associados à sua instituição.</p>
            <p>Disponibilize materiais de apoio e faça sua parte na melhoria do ensino.</p>
            <ul>
                <li>Acesso a relatórios de atividades.</li>
                <li>Espaço para criação de materiais de estudo.</li>
            </ul>
        </>
        ),
    }

    let descriptionTitle = titles[selectedTab];
    let descriptionText = descriptions[selectedTab]

    const updateSelection = (newSelection) => {
        setSelectedTab(newSelection);
        descriptionTitle = titles[selectedTab];
        descriptionText = descriptions[selectedTab]
    }

    return (
        <div className={styles.root}>
        <header className={styles.banner}>
            <div className={styles.bannerText}>Sistema de Monitoria</div>
        </header>
        <main>
            <div className={styles.tabsContainer}>
                <button onClick={() => { updateSelection("alunos")}} className={`${styles.tabButton} ${selectedTab == "alunos" ? styles.selectedTab : ""}`}>Alunos</button>
                <button onClick={() => { updateSelection("monitores")}} className={`${styles.tabButton} ${selectedTab == "monitores" ? styles.selectedTab : ""}`}>Monitores</button>
                <button onClick={() => { updateSelection("instituicoes")}} className={`${styles.tabButton} ${selectedTab == "instituicoes" ? styles.selectedTab : ""}`}>Instituições</button>
            </div>
            <div className={styles.mainContent}>
                <section className={styles.description}>
                    <h2>{descriptionTitle}</h2>
                    {descriptionText}
                </section>
                <div className={styles.dividingLine}/>
                <section className={styles.loginForm}>
                    <h2>Login</h2>
                    <LoginForm tipoUsuario={selectedTab}/>
                </section>
            </div>
        </main>
        </div>
    )
}