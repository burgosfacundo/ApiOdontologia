import React from 'react'
import Patients  from './pages/Patients'
import Dentists from './pages/Dentists'
import Appointments from './pages/Appointments'
import  Home  from './pages/Home'
import {Route,Routes} from 'react-router-dom'



const App = () => {
  return (
    <div>
      <Routes>
        <Route  path='/' element={<Home/>}>
          <Route path='patients' element={<Patients/>}></Route>
          <Route path='/dentists' element={<Dentists/>}></Route>
          <Route path='/appointments' element={<Appointments/>}></Route>
        </Route>
      </Routes>
      </div>
  )
}
export default App

