package com.example.proyectoIntegrador.exception;

public class AppointmentNoContentException extends Exception{
    public AppointmentNoContentException() {
        super("No existen turnos en la base de datos");
    }
}
