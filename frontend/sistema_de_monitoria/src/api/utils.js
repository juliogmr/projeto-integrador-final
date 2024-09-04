export function waitAndRun(ms, func) {
    setTimeout(() => {
        func();
    }, ms);
}

export function saveUserSession(user) {
    localStorage.setItem("user", JSON.stringify(user));
}

export function loadUserSession() {
    const user = JSON.parse(localStorage.getItem("user"));

    return user;
}

export function deleteUserSession() {
    localStorage.removeItem("user");
}