import { useState } from "react";

function AdvForm() {
    const [teamVal, setTeamVal] = useState(undefined)
    const [meetVal, setMeetVal] = useState(undefined)
    const [myForm, setMyForm] = useState({
        name: "",
        mobile: "",
        router: "",
        app: [],
        password: "",
        fileName: ""
    })

    const onSubmit = ($event) => {
        $event.preventDefault()
        let temp = []

        console.log(teamVal)
        console.log(meetVal)

        if(!(teamVal==undefined)){
            temp.push(teamVal)
        }
        if(!(meetVal==undefined)){
            temp.push(meetVal)
        }

        console.log(temp)
        const updatedForm = {...myForm, app: temp}
        setMyForm(updatedForm)
        console.log(updatedForm)
    }

    return (
        <div className="container">
            <div className="row mt-4">
                <div className="col-sm-3"></div>
                <div className="col-sm-6">
                    <div className="card">
                        <form onSubmit={($event) => onSubmit($event)}>
                            <div className="card-header">
                                Please Provide Details
                            </div>
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-md-3 mb-4">
                                        <label>Name: </label>
                                    </div>
                                    <div className="col-md-9">
                                        <input type="text" className="form-control" 
                                        onChange={($event)=>setMyForm({...myForm, name: $event.target.value})}/>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-md-3">
                                        <label>Mobile: </label>
                                    </div>
                                    <div className="col-md-9">
                                        <input type="number" className="form-control" 
                                        onChange={($event)=>setMyForm({...myForm, mobile: $event.target.value})}/>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-md-6">
                                        <label>Are you using router?: </label>
                                    </div>
                                    <div className="col-md-6">
                                        <input type="radio" value="yes" name="router" 
                                        onChange={($event)=>setMyForm({...myForm, router: $event.target.value})}/>
                                        <label> Yes</label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" value="no" name="router" 
                                        onChange={($event)=>setMyForm({...myForm, router: $event.target.value})}/>
                                        <label> No</label>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-md-7">
                                        <label>Are you using following Apps?: </label>
                                    </div>
                                    <div className="col-md-5">
                                        <input type="checkbox" value="teams" name="app" 
                                        onChange={(e)=> e.target.checked ? setTeamVal("teams") : setTeamVal(undefined)}/>
                                        <label> Teams</label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="checkbox" value="meet" name="app" 
                                        onChange={(e)=>e.target.checked ? setMeetVal("meet") : setMeetVal(undefined)}/>
                                        <label> Meet</label>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-sm-3">
                                        <label>Password: </label>
                                    </div>
                                    <div className="col-md-9">
                                        <input type="password" className="form-control" 
                                        onChange={($event)=>setMyForm({...myForm, password: $event.target.value})}/>
                                    </div>
                                </div>
                                <div className="row ">
                                    <div className="col-sm-4">
                                        <label>Upload your ID: </label>
                                    </div>
                                    <div className="col-md-8">
                                        <input type="file" 
                                        onChange={($event)=>setMyForm({...myForm, fileName: $event.target.files[0].name})}/>
                                        <button className="btn btn-info mt-2" disabled>Upload ID</button>
                                    </div>
                                </div>
                            </div>
                            <div className="card-footer">
                                <input type="submit" value="Submit" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdvForm