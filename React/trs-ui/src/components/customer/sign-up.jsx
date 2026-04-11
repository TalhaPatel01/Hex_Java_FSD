import axios from "axios"
import { useState } from "react"
import { Link } from "react-router-dom"

function CustomerSignUp() {
    const [name, setName] = useState(undefined)
    const [email, setEmail] = useState(undefined)
    const [city, setCity] = useState(undefined)
    const [username, setUsername] = useState(undefined)
    const [password, setPassword] = useState(undefined)
    const [errorMsg, setErrorMsg] = useState(undefined)
    const [successMsg, setSuccessMsg] = useState(undefined)

    const signUpApi = "http://localhost:8080/api/customer/sign-up"

    const processSignUp = async (e) => {
        e.preventDefault()

        try {
            await axios.post(signUpApi, {
                    "name": name,
                    "email": email,
                    "city": city,
                    "username": username,
                    "password": password
            })
            setSuccessMsg("Account created, proceed to log in")
            setErrorMsg(undefined)
        }
        catch (err) {
            setErrorMsg(err.message)
            setSuccessMsg(undefined)
        }
    }

return (
    <div className="container">
        <div className="row">
            <div className="col-lg-12 mb-4">
                Navbar with Log in
            </div>
        </div>
        <div className="row mt-4">
            <div className="col-lg-4">
            </div>
            <div className="col-lg-4">
                <div className="card">
                    <div className="card-header">
                        Sign Up
                    </div>
                    <div className="card-body">
                        <form onSubmit={(e) => processSignUp(e)}>
                            {
                                errorMsg == undefined ? "" :
                                    <div className="alert alert-danger mt-4">
                                        {errorMsg}
                                    </div>
                            }
                            {
                                successMsg == undefined ? "" :
                                    <div className="alert alert-primary mt-4">
                                        {successMsg}
                                    </div>
                            }
                            <div className="mt-4">
                                <label>Name: </label>
                                <input type="text" className="form-control" required="required"
                                    onChange={(e) => setName(e.target.value)} />
                            </div>
                            <div className="mt-4">
                                <label>Email: </label>
                                <input type="text" className="form-control" required="required"
                                    onChange={(e) => setEmail(e.target.value)} />
                            </div>
                            <div className="mt-4">
                                <label>City: </label>
                                <input type="text" className="form-control" required="required"
                                    onChange={(e) => setCity(e.target.value)} />
                            </div>
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
                                <input type="submit" value="Sign Up" className="btn btn-primary" />
                            </div>
                            <div className="mt-4">
                                Already have an account? &nbsp;&nbsp;
                                <Link to="/log-in">Log In</Link>
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

export default CustomerSignUp