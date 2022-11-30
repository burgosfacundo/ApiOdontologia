package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable=false)
    private  String name;
    @Column(name="lastName",nullable=false)
    private  String lastName;
    @Column(name="registration",nullable=false,unique = true)
    private  String registration;

    @OneToMany(mappedBy = "dentist",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments= new HashSet<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDentist(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDentist(this);
    }
}
