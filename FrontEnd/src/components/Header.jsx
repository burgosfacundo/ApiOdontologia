import React from 'react'
import {Link} from 'react-router-dom'
import {Navbar,Nav} from 'react-bootstrap'


const Header = () => {
    
  return (
    <Navbar collapseOnSelect expand='lg' bg='dark' variant='dark'>
      <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
        <Navbar.Collapse>
          <Nav>
            <Nav.Link>
              <Link to='/'><h3>Home</h3></Link>
            </Nav.Link>
            <Nav.Link>
              <Link to='/patients'><h3>Pacientes</h3></Link>
            </Nav.Link>
            <Nav.Link>
              <Link to='/dentists'><h3>Odontologos</h3></Link>
            </Nav.Link>
            <Nav.Link>
              <Link to='/appointments'><h3>Turnos</h3></Link>
            </Nav.Link>
          </Nav> 
        </Navbar.Collapse>
    </Navbar>
  )
}

export default Header