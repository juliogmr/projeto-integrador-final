import { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../contexts/userContext";


export default function Dashboard() {
    const { user } = useContext(UserContext);
    const navigate = useNavigate();

    useEffect(() => {
        if (!user) {
                navigate("/")
            }
    }, [])
    

    return (
        <>
            {user && <p>{user.userID}</p>}
            {user && <p>{user.tipoUsuario}</p>}
        </>
    )
}