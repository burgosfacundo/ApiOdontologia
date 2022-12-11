package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.PatientNoContentException;
import com.example.proyectoIntegrador.exception.PatientNotFoundException;
import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getAll() throws PatientNoContentException {
        if(repository.findAll().isEmpty())
            throw new PatientNoContentException();
        return repository.findAll();
    }
    public Patient getById(Long id) throws PatientNotFoundException {
        return repository.findById(id).orElseThrow(PatientNotFoundException::new);
    }
    public Patient getByDni(String dni) throws PatientNotFoundException {
        return repository.findByDni(dni).orElseThrow(PatientNotFoundException::new);
    }
    public void create(Patient patient){repository.save(patient);}
    public void update(Patient patient){repository.save(patient);}
    public void deleteById(Long id){repository.deleteById(id);}

}

