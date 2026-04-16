import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import getAllCustomers from "../../redux/actions/customerAction"

function CustomerList(){
    const dispatch = useDispatch()
    const {customers} = useSelector(state=>state.customerReducer)

    useEffect(()=>{
        dispatch(getAllCustomers())
    },[dispatch])

    return (
        <div>
            {
                customers.map((c,index)=>(
                    <div key={index}>
                        <p>{c.name}---{c.email}</p>
                    </div>
                ))
            }
        </div> 
    )
}

export default CustomerList