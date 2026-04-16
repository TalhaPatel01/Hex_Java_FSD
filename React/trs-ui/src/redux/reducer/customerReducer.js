import {GET_ALL_CUSTOMERS} from "../actions/customerAction"

const initialState = {
    customers: []
}

function customerReducer(state = initialState, action){
    switch(action.type){
        case GET_ALL_CUSTOMERS:
            return {
                ...state,
                customers: action.payload
            }

        default:
            return state
    }
}

export default customerReducer