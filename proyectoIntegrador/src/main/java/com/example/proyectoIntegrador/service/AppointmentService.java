package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.AppointmentNoContentException;
import com.example.proyectoIntegrador.exception.AppointmentNotFoundException;
import com.example.proyectoIntegrador.model.Appointment;
import com.example.proyectoIntegrador.model.AppointmentDTO;
import com.example.proyectoIntegrador.repository.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository repository;
    ObjectMapper mapper;

    public Set<AppointmentDTO> getAll() throws AppointmentNoContentException {
        var appointments = repository.findAll();
        if(appointments.isEmpty())
            throw new AppointmentNoContentException();

        Set<AppointmentDTO> listaDTO = new HashSet<>();
        for (Appointment appointment: appointments){
            listaDTO.add(mapper.convertValue(appointment,AppointmentDTO.class));
        }
        return listaDTO;
    }
    public AppointmentDTO getById(Long id) throws AppointmentNotFoundException {
        var appointment = repository.findById(id);
        if(appointment.isPresent()){
            return mapper.convertValue(appointment,AppointmentDTO.class);
        }else{
            throw new AppointmentNotFoundException();
        }

    }
    private void save(AppointmentDTO appointmentDTO) {
        var appointment = mapper.convertValue(appointmentDTO,Appointment.class);
        repository.save(appointment);
    }
    public void create(AppointmentDTO appointmentDTO){save(appointmentDTO);}

    public void update(AppointmentDTO appointmentDTO){save(appointmentDTO);}
    public void deleteById(Long id){repository.deleteById(id);}
}
