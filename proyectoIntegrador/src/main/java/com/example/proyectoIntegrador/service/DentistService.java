package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.DentistNoContentException;
import com.example.proyectoIntegrador.exception.DentistNotFoundException;
import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.repository.DentistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;



@AllArgsConstructor
@Service
public class DentistService {
    private final DentistRepository repository;

    public List<Dentist> getAll() throws DentistNoContentException {
        if(repository.findAll().isEmpty())
            throw new DentistNoContentException();
        return repository.findAll();
    }
    public Dentist getById(Long id) throws DentistNotFoundException {
        return repository.findById(id).orElseThrow(DentistNotFoundException::new);
    }
    public Dentist getByRegistration(String registration) throws DentistNotFoundException {
        return repository.findByRegistration(registration).orElseThrow(DentistNotFoundException::new);
    }
    public void create(Dentist dentist){repository.save(dentist);}
    public void update(Dentist dentist){repository.save(dentist);}
    public void deleteById(Long id){repository.deleteById(id);}
}
