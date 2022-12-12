package com.example.proyectoIntegrador.service;


import com.example.proyectoIntegrador.exception.AppointmentNoContentException;
import com.example.proyectoIntegrador.exception.BadRequestException;
import com.example.proyectoIntegrador.model.*;
import com.example.proyectoIntegrador.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Optional;

import static com.example.proyectoIntegrador.model.AppUserRoles.ADMIN;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;
    private AppUser user;

    @BeforeEach
    void setUp(){
        user = new AppUser(1L,"Facundo","facundo@gmail.com","1111",ADMIN);
    }

    @Test
    @DisplayName("WHEN we find user by email THEN don´t throws any exception")
    public void findByEmail(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.loadUserByUsername("facundo@gmail.com"));
    }

    @Test
    @DisplayName("WHEN we find user by email THEN throws UsernameNotFoundException")
    public void findByEmailException(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(UsernameNotFoundException.class,()->service.loadUserByUsername("facundo@gmail.com"));
    }

    @Test
    @DisplayName("WHEN we create a user then don´t throws any exception")
    public void createUser(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(user));
    }
    @Test
    @DisplayName("WHEN we create a user with the repeated dni then it throws BadRequestException")
    public void createUserException(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(user));
    }
}
