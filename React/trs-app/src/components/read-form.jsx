import { useState } from "react";

function ReadForm(){
    const [name,setName] = useState('')
    const [username, setUsername] = useState('')
    const [password,setPassword] = useState('')

    const signUp = ($event)=>{
        $event.preventDefault()
        console.log(name)
        console.log(username)
        console.log(password)
    }

    return (
        <div>
            <h2>Enter info</h2>
            <form>
                <div>
                    <label>Enter Name:</label>
                    <input type="text" onChange={($event)=>setName($event.target.value)} />
                </div>
                <div>
                    <label>Enter Username:</label>
                    <input type="text" onChange={($event)=>setUsername($event.target.value)}/>
                </div>
                <div>
                    <label>Enter Password:</label>
                    <input type="password" onChange={($event)=>setPassword($event.target.value)}/>
                </div>
                <div>
                    <button type="submit" onClick={($event)=>signUp($event)}>SignUp</button>
                </div>
            </form>
        </div>
    )
}

export default ReadForm