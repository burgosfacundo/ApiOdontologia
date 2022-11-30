import React, { useEffect, useState } from 'react'
import RowDentist from '../components/RowDentist'
import axios from 'axios';
import Table from 'react-bootstrap/Table'

const Dentists = () => {
  const [dentists,setDentists]= useState([])

    useEffect(()=>{
      const options = {method: 'GET', url: 'http://localhost:8080/dentists/all'};

      axios.request(options)
        .then(function (response){
          setDentists(response.data)})
        .catch(function (error){
           console.error(error);});
    },[])
  return (
    <Table striped bordered hover className='container'>
        <thead>
          <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Registration</th>
          </tr>
        </thead>
        <tbody>
          {dentists.map(dentist => <RowDentist key={dentist.id} dentist={dentist}/>)} 
        </tbody>
        
      </Table>
  )
}

export default Dentists