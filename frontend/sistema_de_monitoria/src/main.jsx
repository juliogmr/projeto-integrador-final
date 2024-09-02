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

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
    },
    {
        path: "/dashboard",
        element: <Dashboard />,
    }
]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <UserProvider>
        <RouterProvider router={router} />
    </UserProvider>
  </StrictMode>,
)
