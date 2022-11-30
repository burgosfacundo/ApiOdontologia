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
        var response = service.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dentist>> getById(@PathVariable Long id) {
        var response = service.getById(id);
        if(response.isPresent()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Dentist dentist){
        service.create(dentist);
        return new ResponseEntity<>("Se registro el dentista",HttpStatus.OK);
    }
    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Dentist dentist){
        service.update(dentist);
        return new ResponseEntity<>("Se modifico el dentista",HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ){
        if(service.deleteById(id)){
            return new ResponseEntity<>("Se elimino el dentista",HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}

