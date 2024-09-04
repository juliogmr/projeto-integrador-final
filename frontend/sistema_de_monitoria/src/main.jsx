import { createContext, StrictMode, useState } from 'react'
import { createRoot } from 'react-dom/client'
import {
    createBrowserRouter,
    RouterProvider,
} from 'react-router-dom'
import Root from './routes/root'
import './index.css'
import Dashboard from './routes/dashboard/dashboard'
import { UserProvider } from './contexts/userContext'
import HomePage from './routes/homepage/homePage'
import Summary from './routes/dashboard/summary/summary'
import Encontrar from './routes/dashboard/encontrar/encontrar'
import Monitorias from './routes/dashboard/monitorias/monitorias'
import Mensagens from './routes/dashboard/mensagens/mensagens'
import Solicitar from './routes/dashboard/solicitar/solicitar'

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        children: [
            {
                path: "/",
                element: <HomePage />
            },
            {
                path: "/dashboard",
                element: <Dashboard />,
                children: [
                    {
                        path: "/dashboard",
                        element: <Summary />
                    },
                    {
                        path: "/dashboard/encontrar/alunos",
                        element: <Encontrar targetString="alunos"/>
                    },
                    {
                        path: "/dashboard/encontrar/monitores",
                        element: <Encontrar targetString="monitores"/>
                    },
                    {
                        path: "/dashboard/monitorias",
                        element: <Monitorias />
                    },
                    {
                        path: "/dashboard/mensagens",
                        element: <Mensagens />
                    },
                    {
                        path: "/dashboard/solicitar",
                        element: <Solicitar />
                    }
                ]
            }
        ]
    },
]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <UserProvider>
        <RouterProvider router={router} />
    </UserProvider>
  </StrictMode>,
)
