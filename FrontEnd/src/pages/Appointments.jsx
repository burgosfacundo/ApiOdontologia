import React, { useEffect, useState } from 'react'
import RowAppointment from '../components/RowAppointmet'
import axios from 'axios';
import Table from 'react-bootstrap/Table'

const Appointments = () => {
  const [appointments,setAppointments]= useState([])

    useEffect(()=>{
      const options = {method: 'GET', url:'http://localhost:8080/appointments/all'};

      axios.request(options)
        .then(function (response){
          setAppointments(response.data)})
        .catch(function (error){
           console.error(error);});
    },[])

    console.log(appointments);
  return (
    <Table striped bordered hover className='container'>
    <thead>
      <tr>
        <th>Id</th>
        <th>Fecha y Hora</th>
        <th>Dentista</th>
        <th>Paciente</th>
      </tr>
    </thead>
    <tbody>
      {appointments.map(appointment => <RowAppointment key={appointment.id} appointment={appointment}/>)} 
    </tbody>
    
  </Table>
  )
}

export default Appointments
