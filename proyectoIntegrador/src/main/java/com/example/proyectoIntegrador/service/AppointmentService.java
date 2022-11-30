package com.example.proyectoIntegrador.service;

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

    public Set<AppointmentDTO> getAll(){
        var appointments = repository.findAll();
        Set<AppointmentDTO> listaDTO = new HashSet<>();
        for (Appointment appointment: appointments){
            listaDTO.add(mapper.convertValue(appointment,AppointmentDTO.class));
        }
        return listaDTO;
    }
    public AppointmentDTO getById(Long id){
        var appointment = repository.findById(id);
        AppointmentDTO dto = null;
        if(appointment.isPresent()){
            dto = mapper.convertValue(appointment,AppointmentDTO.class);
        }
        return dto;
    }

    private void save(AppointmentDTO appointmentDTO) {
        var appointment = mapper.convertValue(appointmentDTO,Appointment.class);
        repository.save(appointment);
    }
    public void create(AppointmentDTO appointmentDTO){save(appointmentDTO);}

    public void update(AppointmentDTO appointmentDTO){save(appointmentDTO);}
    public void deleteById(Long id){repository.deleteById(id);}
}
