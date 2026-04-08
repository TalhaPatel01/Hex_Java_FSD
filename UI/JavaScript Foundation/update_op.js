const ticket = {
    id: 1,
    subject: 'Internet down',
    details: 'Some details',
    priority: 'HIGH',
    status: 'OPEN'
}

// Customer in UI changes the status to CLOSED 

    /**
     * Plan would be to call PUT API in the background in async manner 
     * in UI, we need to update this ticket object and re-render. 
     * But in react, we never update the object, we clone it and then update new/closed object 
     */

const updatedTicket = {
    ...ticket,   // Spead operator - creates a clone of original object
    status:"CLOSED"
}

console.log(ticket)
console.log(updatedTicket)