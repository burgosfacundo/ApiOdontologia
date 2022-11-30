package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist,Long> {

}
