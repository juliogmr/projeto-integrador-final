export async function fazerLogin(tipoUsuario, email, senha) {
    const endpoints = {
        "alunos": "http://localhost:8080/alunos/logar",
        "monitores": "http://localhost:8080/monitores/logar",
        "instituicoes": "http://localhost:8080/instituicoes/logar",
    };

    const url = endpoints[tipoUsuario];

    console.log(`TipoUsuario: ${tipoUsuario}`);
    console.log(`Email: ${email}`);
    console.log(`Password: ${senha}`);
    console.log(`URL: ${url}`);

    try {
        const resp = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, senha}),
        });

        const data = await resp.json();

        console.log(data)
        return data;
    } catch(err) {
        console.log(err);
        return null;
    }
}