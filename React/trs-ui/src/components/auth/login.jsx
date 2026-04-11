import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function Login() {
    const [username, setUsername] = useState(undefined)
    const [password, setPassword] = useState(undefined)
    const [token, setToken] = useState(undefined)
    const [errorMsg, setErrorMsg] = useState(undefined)

    const navigate = useNavigate()

    const loginAPi = "http://localhost:8080/api/auth/login"
    const detailsApi = "http://localhost:8080/api/auth/user-details"

    const processLogin = async (e) => {
        e.preventDefault()

        try {
            // Generate encoded string from username and password using btoa (binary to ASCII)
            let encodedString = window.btoa(username + ":" + password)

            const config = {
                headers: {
                    "Authorization": "Basic " + encodedString
                }
            };

            const response = await axios.get(loginAPi, config)
            setToken(response.data.token)
            // console.log(response.data)
            // console.log(response.data.token)
            localStorage.setItem("token", response.data.token)

            //call user details api
            const detailsConfig = {
                headers: {
                    "Authorization": "Bearer " + response.data.token
                }
            };

            const detailsResponse = await axios.get(detailsApi, detailsConfig)
            switch (detailsResponse.data.role) {
                case "CUSTOMER":
                    navigate("/customer-dashboard")
                    break;
                case "EXECUTIVE":
                    navigate("/executive-dashboard")
                    break;
                case "ADMIN":
                    navigate("/admin-dashboard")
                    break;
            }

        }
        catch (err) {
            setErrorMsg("Invalid Credentials")
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-lg-12 mb-4">
                    Navbar with sign up
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-lg-4">
                </div>
                <div className="col-lg-4">
                    <div className="card">
                        <div className="card-header">
                            Login
                        </div>
                        <div className="card-body">
                            <form onSubmit={(e) => processLogin(e)}>
                                {
                                    errorMsg==undefined ? "" :
                                    <div className="alert alert-danger mt-4">
                                        {errorMsg}
                                    </div>
                                }
                                <div className="mt-4">
                                    <label>Username: </label>
                                    <input type="text" className="form-control" required="required"
                                        onChange={(e) => setUsername(e.target.value)} />
                                </div>
                                <div className="mt-4">
                                    <label>Password: </label>
                                    <input type="password" className="form-control" required="required"
                                        onChange={(e) => setPassword(e.target.value)} />
                                </div>
                                <div className="mt-4">
                                    <input type="submit" value="Login" className="btn btn-primary" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-lg-12">
                    Footer goes here with Copyright
                </div>
            </div>
        </div>
    )
}

export default Login