import React from 'react'

const RowPatient = ({patient}) => {
  return (
    <tr>
        <td>{patient.id}</td>
        <td>{patient.name}</td>
        <td>{patient.lastName}</td>
        <td>{patient.dni}</td>
        <td>{patient.dischargeDate}</td>
        <td>{patient.address}</td>
    </tr>
  )
}

export default RowPatient