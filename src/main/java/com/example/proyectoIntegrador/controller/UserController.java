package com.example.proyectoIntegrador.controller;


import com.example.proyectoIntegrador.config.jwt.JwtUtil;
import com.example.proyectoIntegrador.config.jwt.model.AuthenticationRequest;
import com.example.proyectoIntegrador.config.jwt.model.AuthenticationResponse;
import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.AppUser;
import com.example.proyectoIntegrador.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
public class UserController {
    private UserService service;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAll() throws AppUserNoContentException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable Long id) throws AppUserNotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody AppUser user){
        try{
            service.create(user);
            return new ResponseEntity<>("Se registro el usuario", HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody AppUser appUser){
        try{
            service.update(appUser);
            return new ResponseEntity<>("Se modifico el usuario",HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Se elimino el usuario",HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }
        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));
    }
}
