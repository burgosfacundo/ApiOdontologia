package com.example.proyectoIntegrador.model;

import java.time.LocalDateTime;

public record AppointmentDTO (long id, LocalDateTime dayTime, Dentist dentist,Patient patient){
}
