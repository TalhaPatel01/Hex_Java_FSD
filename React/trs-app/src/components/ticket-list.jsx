function TicketList(){

    const tickets = [
    {
        id: 1,
        subject: 'Internet down',
        details: 'Some details',
        priority: 'HIGH',
        status: 'OPEN'
    },
    {
        id: 2,
        subject: 'Internet slow',
        details: 'Some details',
        priority: 'MEDIUM',
        status: 'CLOSED'
    },
    {
        id: 3,
        subject: 'Internet dead',
        details: 'Some details',
        priority: 'HIGH',
        status: 'OPEN'
    }]

    return(
        <div>
            <h2>Ticket List</h2>
            {
                tickets.map((ticket,index)=>(    //Each child in a list should have a unique "key" prop.
                    <li key={index}>
                        {index+1}. {ticket.subject} -- {ticket.priority} -- {ticket.status}
                    </li>
                ))
            }
        </div>
    )
}

export default TicketList