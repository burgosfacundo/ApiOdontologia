package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.exception.AppointmentNoContentException;
import com.example.proyectoIntegrador.exception.AppointmentNotFoundException;
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
    public ResponseEntity<Set<AppointmentDTO>> getAll() throws AppointmentNoContentException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id) throws AppointmentNotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody AppointmentDTO appointmentDTO){
        try{
            service.create(appointmentDTO);
            return new ResponseEntity<>("Se registro el turno",HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody AppointmentDTO appointmentDTO){
        try{
            service.update(appointmentDTO);
            return new ResponseEntity<>("Se modifico el turno",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Se elimino el turno",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

