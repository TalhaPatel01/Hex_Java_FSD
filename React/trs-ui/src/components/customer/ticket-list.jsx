import axios from "axios"
import { useState, useEffect } from "react"
import { useNavigate, useParams } from "react-router-dom"

function TicketList() {
    const [tickets,setTickets] = useState([])
    const {status} = useParams()
    const navigate = useNavigate()

    const api = "http://localhost:8080/api/ticket/customer/v1"
    const updateAPI = "http://localhost:8080/api/ticket/update/status/"

    useEffect(()=>{
        const getTickets = async ()=>{
            const config = {
                headers:{
                    "Authorization" : "Bearer " + localStorage.getItem("token")
                }
            }
            const response = await axios.get(api,config)
            setTickets(response.data)
            filter(response.data)
        }

        const filter = (ticketData)=>{
            let statusValue = status.split(" ")[0]
            // console.log(statusValue)
            let filteredData = ticketData.filter(ticket=>ticket.status===statusValue)
            setTickets([...filteredData])
        }

        getTickets()
    },[status])

    const closeTicket = async (ticketId)=>{
        const config = {
            headers: {
                "Authorization" : "Bearer " + localStorage.getItem("token")
            }
        }

        try{
            await axios.put(updateAPI + `${ticketId}/v2?ticketStatus=CLOSED`,{},config)
            //let filteredTicket = [...tickets].filter(ticket=>ticket.id!=ticketId)
            //setTickets(filteredTicket)
            navigate("/customer-dashboard/show-ticket/CLOSED")
        }
        catch(err){
            console.log(err.message)
        }
    }

    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Sr No.</th>
                        <th scope="col">Subject</th>
                        <th scope="col">Status</th>
                        <th scope="col">Priority</th>
                        <th scope="col">Creation Date</th>
                        <th scope="col">Executive Name</th>
                        <th scope="col">Executive Title</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        tickets.map((ticket,index)=>(
                            <tr key = {index}>
                                <th scope="row">{index+1}</th>
                                <td>{ticket.subject}</td>
                                <td>{ticket.status}</td>
                                <td>{ticket.priority}</td>
                                <td>{ticket.createdAt}</td>
                                <td>{ticket.executiveName}</td>
                                <td>{ticket.executiveJobTitle}</td>
                                <td><button className="btn btn-warning" onClick={()=>closeTicket(ticket.id)}>Close Ticket</button></td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default TicketList