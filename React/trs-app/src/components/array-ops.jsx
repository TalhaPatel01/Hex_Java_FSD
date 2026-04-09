import { useState } from "react";
import {tickets_data} from "../sample-data/ticket-data"

function ArrayOps(){
    const [tickets,setTickets] = useState(tickets_data)
    const [ticketsBackUp, setTicketsBackUp] = useState(tickets)

    const highPriorityFilter = ()=>{
        const temp = [...tickets].filter(ticket=>ticket.priority=="HIGH")
        setTickets(temp)
    }

    const openStatusFilter = ()=>{
        const temp = [...tickets].filter(ticket=>ticket.status=="OPEN")
        setTickets(temp)
    }

    const showAllTickets = ()=>{
        // Here, I am setting the value of all tickets to tickets state. 
        // restoring all original values
        setTickets(ticketsBackUp)
    }

    return(
        <div>
            <h2>All tickets</h2>
            <hr/>
                <button onClick={highPriorityFilter}> Show HIGH Priority </button> 
                <button onClick={openStatusFilter}> Show State OPEN</button>
                <button onClick={showAllTickets}> Show All Tickets</button>
            <hr />

            {
                tickets.map((ticket,index)=>(
                    <li key={index}>
                        {index+1}. {ticket.subject}--{ticket.priority}--{ticket.status}
                    </li>
                ))
            }
        </div>
    )
}

export default ArrayOps