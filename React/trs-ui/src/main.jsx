import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import CustomerDashboard from "./components/customer/customer-dashboard.jsx"
import ExecutiveDashboard from "./components/executive/executive-dashboard.jsx"
import AdminDashboard from "./components/admin/admin-dashboard.jsx"
import CustomerSignUp from "./components/customer/sign-up.jsx"
import Login from './components/auth/login.jsx'

const routes = createBrowserRouter([
    {
        path: "",
        element: <App/>
    },
    {
        path: "/customer/sign-up",
        element: <CustomerSignUp/>
    },
    {
        path: "/log-in",
        element: <Login/>
    },
    {
        path: "/customer-dashboard",
        element: <CustomerDashboard />
    },
    {
        path: "/executive-dashboard",
        element: <ExecutiveDashboard />
    },
    {
        path: "/admin-dashboard",
        element: <AdminDashboard />
    }
])

createRoot(document.getElementById('root')).render(
    <RouterProvider router={routes}> 
        <App />
    </RouterProvider>
)