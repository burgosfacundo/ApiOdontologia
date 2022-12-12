package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.BadRequestException;
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
    public void create(Patient patient) throws BadRequestException {
        if(repository.findByDni(patient.getDni()).isPresent())
            throw new BadRequestException("El paciente con el dni: " + patient.getDni() + " ya existe en la base de datos");
        repository.save(patient);
    }
    public void update(Patient patient) throws PatientNotFoundException {
        if(repository.findById(patient.getId()).isEmpty()) throw new PatientNotFoundException();
        repository.save(patient);
    }
    public void deleteById(Long id) throws PatientNotFoundException {
        if(repository.findById(id).isEmpty()) throw new PatientNotFoundException();
        repository.deleteById(id);
    }

}

