export async function fazerLogin(tipoUsuario, email, senha) {
    const endpoints = {
        "alunos": "http://localhost:8080/alunos/logar",
        "monitores": "http://localhost:8080/monitores/logar",
        "instituicoes": "http://localhost:8080/instituicoes/logar",
    };

    const url = endpoints[tipoUsuario];

    try {
        const resp = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, senha}),
        });

        let data = await resp.json();

        if (!resp.ok) {
            throw data.message;
        }

        const user = { ...data, tipoUsuario: tipoUsuario };
        return user;
    } catch(err) {
        console.log("Erro: " + err);
        return null;
    }
}

export async function buscarUsuarios(target, nome, disciplina) {
    const endpoints = {
        "monitores": "http://localhost:8080/monitores",
        "alunos": "http://localhost:8080/alunos",
    };

    let url = endpoints[target] + "?nome=" + nome + "&disciplina=" + disciplina;

    try {
        const resp = await fetch(url);
        const resultados = await resp.json();

        return resultados;

    } catch(err) {
        console.log("Erro: " + err);
        return [];
    }
}

export async function buscarMonitorias(user) {
    const url = `http://localhost:8080/${user.tipoUsuario}/${user.id}/monitorias`;

    try {
        const resp = await fetch(url);
        const resultados = await resp.json();

        const monitorias = resultados.map((resultado) => {
            return {
                ...resultado,
                data: new Date(resultado.data),
            }
        });

        monitorias.sort((a, b) => {
            return a.data - b.data;
        });

        return monitorias;
    } catch (err) {
        console.log("Erro: " + err);
        return [];
    }
}

export function monitoriaVencida(monitoria) {
    return monitoria.data < Date.now();
}

export function filtrarMonitoriasVencidas(monitorias) {
    return monitorias.filter((monitoria) => {
            return !monitoriaVencida(monitoria);
        });
}

export async function buscarMensagens(user) {
    const url = `http://localhost:8080/${user.tipoUsuario}/${user.id}/mensagens`;

    try {
        const resp = await fetch(url);
        const resultados = await resp.json();

        let mensagens = resultados.map((resultado) => {
            return {
                ...resultado,
                data: new Date(resultado.data),
            }
        });

        mensagens.sort((a, b) => {
            return a.data - b.data;
        });

        if(user.tipoUsuario === "alunos") {
            mensagens = mensagens.filter((mensagem) => mensagem.tipo !== "Solicitação de Monitoria");
        }

        if(user.tipoUsuario === "monitores") {
            mensagens = mensagens.filter((mensagem) => mensagem.tipo !== "Monitoria Aceita" && mensagem.tipo !== "Monitoria Recusada");
        }

        return mensagens;
    } catch (err) {
        console.log("Erro: " + err);
        return [];
    }
}

export async function deletarMensagem(mensagem) {
    const url = `http://localhost:8080/mensagens/${mensagem.id}`;

    try {
        const resp = await fetch(url, {
            method: "DELETE",
            body: {
                respondida: true,
            },
        });

        return resp.ok;
    } catch(err) {
        console.log("Erro: " + err);
        return false;
    }
}

export async function aceitarMonitoria(monitoria) {
    const url = `http://localhost:8080/monitorias/${monitoria.id}/aceitar`;

    try {
        const resp = await fetch(url, {
            method: "PUT"
        });
        
        return resp.ok;
    } catch(err) {
        console.log("Erro: " + err);
        return false;
    }
}

export async function recusarMonitoria(monitoria) {
    const url = `http://localhost:8080/monitorias/${monitoria.id}/recusar`;

    try {
        const resp = await fetch(url, {
            method: "PUT"
        });
        
        return resp.ok;
    } catch(err) {
        console.log("Erro: " + err);
        return false;
    }
}