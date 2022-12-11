package com.example.proyectoIntegrador.controller;
import com.example.proyectoIntegrador.exception.DentistNoContentException;
import com.example.proyectoIntegrador.exception.DentistNotFoundException;
import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.service.DentistService;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@CrossOrigin( origins = "http://localhost/8080")
@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService service;


    @GetMapping("/all")
    public ResponseEntity<List<Dentist>> getAll() throws DentistNoContentException {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getById(@PathVariable Long id) throws DentistNotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/registration")
    public ResponseEntity<Dentist> getByRegistration(@RequestBody String registration) throws DentistNotFoundException {
        return ResponseEntity.ok(service.getByRegistration(registration));
    }
    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Dentist dentist){
        try{
            service.create(dentist);
            return new ResponseEntity<>("Se registro el odontologo",HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Dentist dentist){
        try{
            service.update(dentist);
            return new ResponseEntity<>("Se modifico el odontologo",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Se elimino el odontologo",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

