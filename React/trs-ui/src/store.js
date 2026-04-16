import { applyMiddleware, combineReducers, createStore } from "redux";
import { thunk } from "redux-thunk";
import customerReducer from "./redux/reducer/customerReducer";

const reducers = combineReducers({
    customerReducer: customerReducer
})

export const store = createStore(reducers, applyMiddleware(thunk))