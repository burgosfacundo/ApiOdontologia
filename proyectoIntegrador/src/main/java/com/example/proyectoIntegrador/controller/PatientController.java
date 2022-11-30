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
        var response = service.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getById(@PathVariable Long id){
        var response = service.getById(id);
        if(response.isPresent()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Patient patient){
        service.create(patient);
        return new ResponseEntity<>("Se registro el paciente",HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Patient patient){
        service.update(patient);
        return new ResponseEntity<>("Se modifico el paciente",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return new ResponseEntity<>("Se elimino el paciente",HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}
