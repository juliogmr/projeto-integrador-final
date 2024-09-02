import { createContext, StrictMode, useState } from 'react'
import { createRoot } from 'react-dom/client'
import {
    createBrowserRouter,
    RouterProvider,
} from 'react-router-dom'
import Root from './routes/root'
import './index.css'
import Dashboard from './components/dashboard'
import { UserProvider } from './contexts/userContext'
import HomePage from './components/homePage'

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
                element: <Dashboard />
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
