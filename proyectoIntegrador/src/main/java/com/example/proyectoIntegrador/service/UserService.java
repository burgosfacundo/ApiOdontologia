package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.AppUser;
import com.example.proyectoIntegrador.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<AppUser> getAll() throws AppUserNoContentException {
        if(repository.findAll().isEmpty())
            throw new AppUserNoContentException();
        return repository.findAll();
    }

    public AppUser getById(Long id) throws AppUserNotFoundException {
        return repository.findById(id).orElseThrow(AppUserNotFoundException::new);
    }

    public void create(AppUser user) throws BadRequestException {
        if(repository.findByEmail(user.getEmail()).isPresent())
            throw new BadRequestException("El usuario con el email: " + user.getEmail() + " ya existe en la base de datos");
        if (passwordEncoder != null) {
            var appUser = new AppUser(null, user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getRol());
            repository.save(appUser);
        }
    }

    public void update(AppUser appUser) throws AppUserNotFoundException {
        if(repository.findById(appUser.getId()).isEmpty()) throw new AppUserNotFoundException();
        repository.save(appUser);
    }

    public void deleteById(Long id) throws AppUserNotFoundException {
        if(repository.findById(id).isEmpty()) throw new AppUserNotFoundException();
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No se ha encontrado el email: " + email));
    }
}
