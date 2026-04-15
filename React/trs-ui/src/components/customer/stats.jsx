import { useEffect, useState } from "react"
import axios from "axios"

function Stats(){
    const statsApi = "http://localhost:8080/api/ticket/stats"
    const [stats, setStats] = useState([])

    useEffect(()=>{
        const fetchStats = async ()=>{
            const config={
                headers:{
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
            try{
                const response = await axios.get(statsApi,config)
                setStats(response.data)
            }
            catch(err){
                console.log(err.message)
            }
        }
        fetchStats()
    },[])

    return (
        <div className="card">
            <div className="card-body">
                    <div className="row">
                        {
                            stats.map((stat,index)=>(
                                <div className="col-sm-4" key={index}>
                                    <div className="card">
                                        <div className="card-body">
                                            <div className="text-center">
                                                <h4>{stat.status}</h4>
                                                <h4>{stat.count}</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
            </div>
        </div>
    )
}

export default Stats