package com.example.proyectoIntegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dayTime",nullable=false)
    private LocalDateTime dayTime;

    @ManyToOne
    @JoinColumn(name = "dentist_id",nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;

}

