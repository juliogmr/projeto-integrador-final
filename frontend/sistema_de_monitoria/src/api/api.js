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
    const url = `http://localhost:8080/${user.tipoUsuario}/minhasMonitorias/${user.id}`;

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