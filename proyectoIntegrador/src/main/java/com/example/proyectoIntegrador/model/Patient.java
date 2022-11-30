package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable=false)
    private  String name;
    @Column(name="lastName",nullable=false)
    private  String lastName;
    @Column(name="dni",nullable=false,unique = true)
    private  String dni;
    @Column(name="dischargeDate",nullable=false)
    private  LocalDate dischargeDate;
    @Column(name="address",nullable=false)
    private String address;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this);
    }
}
