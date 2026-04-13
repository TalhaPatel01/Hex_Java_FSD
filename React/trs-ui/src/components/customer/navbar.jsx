import { Link, useNavigate } from "react-router-dom"

function NavBar() {
    const navigate = useNavigate()

    const logout = ()=>{
        localStorage.clear()
        navigate("/log-in")
    }

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <Link className="navbar-brand" to='/customer-dashboard'>TRS</Link>

                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" to="/customer-dashboard">Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/tickets">Tickets</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/plans">Plans</Link>
                        </li>

                    </ul>
                    <form className="d-flex">
                        <button className="btn btn-outline-success" onClick={logout}>Logout</button>
                    </form>
                </div>
            </div>
        </nav>
    )
}

export default NavBar