package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getAll(){return repository.findAll();}
    public Optional<Patient> getById(Long id){return repository.findById(id);}
    public Optional<Patient> getByDni(String dni){return repository.findByDni(dni);}
    public void create(Patient patient){repository.save(patient);}
    public void update(Patient patient){repository.save(patient);}
    public void deleteById(Long id){repository.deleteById(id);}

}

