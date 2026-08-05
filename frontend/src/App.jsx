import { useState } from 'react'
import './App.css'
import Welcome from './components/Welcome'
import Auschreibung from './components/Auschreibung'

function App() {
  const [currentView, setCurrentView] = useState('welcome')

  if (currentView === 'welcome') {
    return <Welcome onStart={() => setCurrentView('auschreibung')} />
  }
  if (currentView === 'auschreibung') {
    return <Auschreibung />
  }

  
}

export default App
