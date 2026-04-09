import ArrayOps from "./components/array-ops"
import StateDemo from "./components/state-demo"
import TicketList from "./components/ticket-list"

function App() {

  return (
    <div>
      <h1>Welcome to React!!!</h1>
      <TicketList/>
      <hr/>
      <StateDemo/>
      <hr/>
      <ArrayOps/>
    </div>
  )
}

export default App