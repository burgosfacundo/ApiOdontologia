package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Dentist;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DentistServiceTest {

    @Autowired
    private DentistService service;

    @Test
    @DisplayName("when a dentist object is created in the h2 database")
    public void createDentist(){
        //GIVEN
        var name = "Facundo";
        var lastName = "Burgos";
        var registration = "ABC";
        var dentist = new Dentist();
        dentist.setName(name);
        dentist.setLastName(lastName);
        dentist.setRegistration(registration);

        //WHEN
        service.create(dentist);

        //THEN
        var dentist2 = service.getById(1L);
        assertNotNull(dentist2);
    }

    @Test
    @DisplayName("when a dentist object is modified in the h2 database")
    public void modifyDentist(){
        //GIVEN
        var name = "Zinedine";
        var lastName = "Zidane";
        var registration = "5";
        var dentist = new Dentist();
        dentist.setName(name);
        dentist.setLastName(lastName);
        dentist.setRegistration(registration);

        service.create(dentist);

        //WHEN
        var dentist2 = new Dentist();
        dentist2.setId(2L);
        dentist2.setName("Lionel");
        dentist2.setLastName("Messi");
        dentist2.setRegistration("10");

        service.update(dentist2);

        //THEN
        var dentist3= service.getById(2L);
        assertEquals(dentist2.getName(),dentist3.get().getName());
        assertEquals(dentist2.getLastName(),dentist3.get().getLastName());
        assertEquals(dentist2.getRegistration(),dentist3.get().getRegistration());

    }
}