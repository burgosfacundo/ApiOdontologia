package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.service.PatientService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor

@CrossOrigin( origins = "http://localhost/8080")
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService service;

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll(){
        try{
            return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/dni")
    public ResponseEntity<Optional<Patient>> getByDni(@RequestBody String dni){
        try{
            return new ResponseEntity<>(service.getByDni(dni),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Patient patient){
        try{
            service.create(patient);
            return new ResponseEntity<>("Se registro el paciente",HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Patient patient){
        try{
            service.update(patient);
            return new ResponseEntity<>("Se modifico el paciente",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Se elimino el paciente",HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
