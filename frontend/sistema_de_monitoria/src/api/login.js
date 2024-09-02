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
        console.log(err);
        return null;
    }
}