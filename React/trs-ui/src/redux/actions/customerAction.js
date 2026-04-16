import axios from "axios"

export const GET_ALL_CUSTOMERS = "GET_ALL_CUSTOMERS"

function getAllCustomers(){

    return async (dispatch)=>{
        const response = await axios.get("http://localhost:8080/api/customer/get-all",{
            headers:{
                "Authorization" : "Bearer " + localStorage.getItem("token")
            }
        })

        dispatch({
            type: GET_ALL_CUSTOMERS,
            payload: response.data
        })
    }
}

export default getAllCustomers