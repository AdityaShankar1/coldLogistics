import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css' // <--- Tailwind MUST be imported here first
import App from './App.jsx'
import './index.css' // <--- Sometimes adding it here as well helps

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)