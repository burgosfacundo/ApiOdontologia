package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.repository.DentistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class DentistService {
    private final DentistRepository repository;

    public List<Dentist> getAll(){return repository.findAll();}
    public Optional<Dentist> getById(Long id){return repository.findById(id);}
    public Optional<Dentist> getByRegistration(String registration){return repository.findByRegistration(registration);}
    public void create(Dentist dentist){repository.save(dentist);}
    public void update(Dentist dentist){repository.save(dentist);}
    public void deleteById(Long id){repository.deleteById(id);}
}
