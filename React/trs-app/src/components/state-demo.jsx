import { useState } from "react";

function StateDemo(){
    let count = 0   //normal variable
    const [stateCount,setStateCount] = useState(0)   //state variable

    const incrCount = ()=>{
        count = count+1
        console.log(`Count: ${count}`)
    }

    const incrStateCount = ()=>{
        setStateCount(stateCount+1)
        console.log(`State Count:${stateCount}`)
    }

    return(
        <div>
            <button onClick={incrCount}>Increment Count</button>
            &nbsp;&nbsp;
            {`Count: ${count}`}
            <hr/>
            <button onClick={incrStateCount}>Increment State Count</button>
            &nbsp;&nbsp;
            {`State Count: ${stateCount}`}
        </div>
    )
}

export default StateDemo

/**
 TWO Variables:
 1. Normal variable 
 2. State Variable  
  
 useState() is a React hook
 
 In Normal Variable, Even if the value of count changes, React does not re-render it, 
 so we don't see the latest value on screen

 In state variable, when the value gets updated, React re-renders that value and so we
 see the updated value instantly on screen 

 Hence as devs, we must always use state variables. 

 */