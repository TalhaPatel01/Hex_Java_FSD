import ArrayOps from "./components/array-ops"
import ReadForm from "./components/read-form"
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
      <hr/>
      <ReadForm/>
    </div>
  )
}

export default App