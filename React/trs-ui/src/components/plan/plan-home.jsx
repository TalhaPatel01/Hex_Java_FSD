import PlanDetails from "./plan-details"
import PlanList from "./plan-list"

function PlanHome(){
    return(
        <div className="row mt-4">
            <div className="col-sm-4">
                <PlanDetails />
            </div>
            <div className="col-sm-4">
                <PlanList />
            </div>
        </div>
    )
}

export default PlanHome