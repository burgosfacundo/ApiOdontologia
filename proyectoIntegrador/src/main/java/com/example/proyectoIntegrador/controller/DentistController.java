package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Dentist;


import com.example.proyectoIntegrador.service.DentistService;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@AllArgsConstructor

@CrossOrigin( origins = "http://localhost/8080")
@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService service;

    @GetMapping("/all")
    public ResponseEntity<List<Dentist>> getAll(){
        try{
            return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dentist>> getById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/registration")
    public ResponseEntity<Optional<Dentist>> getByRegistration(@RequestBody String registration) {
        try{
            return new ResponseEntity<>(service.getByRegistration(registration),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
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

