const response = {
    totalElements: 3,
    totalPages: 1,
    data: [
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
    }
]
}

const {totalElements,totalPages,data} = response
const tickets = data

const dataToRender = tickets.map(ticket=>{
    const {subject,priority,status} = ticket
    return `${subject}--${priority}--${status}`
})

console.log(dataToRender)