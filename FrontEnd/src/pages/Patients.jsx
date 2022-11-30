import React ,{ useState,useEffect } from 'react'
import axios from 'axios';
import Table from 'react-bootstrap/Table'
import RowPatient from '../components/RowPatient';

const Patients = () => {
    const [patients,setPatients]= useState([])

    useEffect(()=>{
      
      const options = {method: 'GET', url: 'http://localhost:8080/patients/all'};

      axios.request(options)
        .then(function (response){
          setPatients(response.data)})
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
            <th>Dni</th>
            <th>Fecha de Alta</th>
            <th>Domicilio</th>
          </tr>
        </thead>
        <tbody>
          {patients.map(patient => <RowPatient key={patient.id} patient={patient}/>)} 
        </tbody>
        
      </Table>
    )
}

export default Patients
