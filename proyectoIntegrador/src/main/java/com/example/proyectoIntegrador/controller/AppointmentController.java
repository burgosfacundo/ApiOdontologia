package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.AppointmentDTO;
import com.example.proyectoIntegrador.service.AppointmentService;
import lombok.AllArgsConstructor;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@AllArgsConstructor

@RestController
@CrossOrigin( origins = "http://localhost/8080")
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService service;

    @GetMapping("/all")
    public ResponseEntity<Set<AppointmentDTO>> getAll(){
        var response =service.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id){
        var response = service.getById(id);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody AppointmentDTO appointmentDTO){
        service.update(appointmentDTO);
        return new ResponseEntity<>("Se registro el turno",HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody AppointmentDTO appointmentDTO){
        service.update(appointmentDTO);
        return new ResponseEntity<>("Se modifco el turno",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return new ResponseEntity<>("Se elimino el turno",HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}

