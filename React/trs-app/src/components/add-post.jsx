import axios from "axios";
import { useState } from "react";

function AddPost(){
    const [title, setTitle] = useState(undefined)
    const [body, setBody] = useState(undefined)
    const [userId, setUserId] = useState(undefined)
    const [errorMsg, setErrorMsg] = useState(undefined)
    const [successMsg, setSuccessMsg] = useState(undefined)
    const api = "https://jsonplaceholder.typicode.com/posts1"

    const addPost =async (e)=>{
        e.preventDefault()

        try{
            const response =await axios.post(api,{
                title : title,
                body: body,
                userId: userId
            })
            setSuccessMsg("Post added to DB")
            setErrorMsg(undefined)
        }
        catch(err){
            setErrorMsg(err.message)
            setSuccessMsg(undefined)
        }
    }

    return(
        <div className="container mt-4">
            <form onSubmit={(e)=>addPost(e)}>
                <div className="row">
                    <div className="col-lg-12">
                        <div className="card">
                            <div className="card-header">
                                <p>Add Post Details</p>
                            </div>
                            <div className="card-body">
                                {
                                    errorMsg == undefined ? "" :
                                    <div className="row">
                                        <div className="col-lg-12">
                                            <div className="alert alert-danger">
                                                {errorMsg}
                                            </div>
                                        </div>
                                    </div>
                                }
                                {
                                    successMsg == undefined ? "" :
                                    <div className="row">
                                        <div className="col-lg-12">
                                            <div className="alert alert-primary">
                                                {successMsg}
                                            </div>
                                        </div>
                                    </div>
                                }
                                <div className="row">
                                    <div className="col-sm-3">
                                        <label>Enter Post Title</label>
                                    </div>
                                    <div className="col-md-8">
                                        <input type="text" className="form-control" required="required"
                                        onChange={(e)=>setTitle(e.target.value)}/>
                                    </div>
                                </div>
                                <div className="row mt-2">
                                    <div className="col-sm-3">
                                        <label>Enter User Id</label>
                                    </div>
                                    <div className="col-md-8">
                                        <input type="number" className="form-control" required="required"
                                        onChange={(e)=>setUserId(e.target.value)}/>
                                    </div>
                                </div>
                                <div className="row mt-2">
                                    <div className="col-sm-3">
                                        <label>Enter Post Details</label>
                                    </div>
                                    <div className="col-md-8">
                                        <textarea className="form-control" required="required"
                                        onChange={(e)=>setBody(e.target.value)}/>
                                    </div>
                                </div>
                            </div>
                            <div className="card-footer">
                                <div className="row">
                                    <div className="col-sm-3">
                                        <input type="submit" value = "Add Post" className="btn btn-secondary" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default AddPost