import { useState } from 'react'

import './App.css'
import NavBar from './components/NavBar.tsx'
import CreateFormation from './pages/CreateFormation.tsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <NavBar />
      <CreateFormation />

    </>
  )
}

export default App
