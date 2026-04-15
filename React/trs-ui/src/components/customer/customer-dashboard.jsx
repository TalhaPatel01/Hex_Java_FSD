import {Outlet ,useNavigate } from "react-router-dom"
import NavBar from "./navbar"
import { useEffect, useState } from "react"
import axios from "axios"
import Profile from "./profile"
import Stats from "./stats"

function CustomerDashboard() {
    const [customer, setCustomer] = useState(undefined)
    const navigate = useNavigate()

    const getApi = "http://localhost:8080/api/customer/get-one"

    useEffect(() => {
        const fetchCustomer = async () => {
            const config = {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
            try {
                const response = await axios.get(getApi,config)
                setCustomer(response.data)
            }
            catch (err) {
                navigate("/log-in")
            }
        }

        fetchCustomer()
    }, [])

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <NavBar />
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-sm-3">
                    <Profile/>
                </div>
                <div className="col-md-9">
                    <Stats/>
                </div>
            </div>
            <div className="col-mt-4">
                <div className="col-lg-12">
                    <Outlet/>
                </div>
            </div>
        </div>
    )
}

export default CustomerDashboard