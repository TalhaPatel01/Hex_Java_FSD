import { useState, useEffect } from "react"

function UserList() {
    const [users, setUsers] = useState([])
    const apiUrl = "https://jsonplaceholder.typicode.com/users"

    useEffect(() => {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => setUsers(data))
    }, []) // [] this ensures that this useEffect fn gets called only once 

    return (
        <div className="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">City</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Company Name</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((u, index) => (
                            <tr>
                                <td scope="row">{index + 1}</td>
                                <td scope="row">{u.name}</td>
                                <td scope="row">{u.username}</td>
                                <td scope="row">{u.email}</td>
                                <td scope="row">{u.address.city}</td>
                                <td scope="row">{u.phone}</td>
                                <td scope="row">{u.company.name}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default UserList