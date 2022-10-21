package com.service.appointmentme.appointment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AppointmentController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAppointments(){
        List<Appointment> appointments = appointmentRepository.findAll();
        return ResponseEntity.accepted().body(appointments);
    }

    @PostMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment){
        logger.info("enter create appintment");
        appointment.validateTimeData();
        Appointment newAppointment = appointmentRepository.save(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("appointment/{id}").buildAndExpand(newAppointment.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
