import { useEffect, useState } from "react"
import axios from "axios"

function ToDoList() {
    const [todos, setTodos] = useState([])
    const [errorMsg, setErrorMsg] = useState(undefined)

    const apiPath = "https://jsonplaceholder.typicode.com/todos"

    useEffect(() => {
        const getAllTodos = async () => {
            try {
                const response = await axios.get(apiPath)
                setTodos(response.data)
                setErrorMsg(undefined)
            }
            catch (err) {
                setErrorMsg(err.message)
            }
        }
        getAllTodos()
    }, [])

    return (
        <div className="container">
            <h1>Todo List</h1>

            {
                errorMsg ? (
                    <div className="alert alert-danger">
                        {errorMsg}
                    </div>
                ) : null
            }

            {
                todos.map((todo,index)=>(
                    <div className="row mt-2" key={index}>
                        <div className="col-lg-12">
                            <div className="card">
                                <div className="card-header">
                                    {todo.title}
                                </div>
                                <div className="card-body">
                                    <p>Completed: {todo.completed == true ? "YES" : "PENDING"}</p>
                                </div>
                                <div className="card-footer">
                                    Some action buttons (delete,update)
                                </div>
                            </div>
                        </div>
                    </div>
                ))
            }
        </div>
    )
}

export default ToDoList