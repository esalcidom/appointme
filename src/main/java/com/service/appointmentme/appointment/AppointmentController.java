package com.service.appointmentme.appointment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.*;

@RestController
public class AppointmentController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value = "/db/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllDbAppointments(){
        List<Appointment> appointments = appointmentRepository.findAll();
        return ResponseEntity.accepted().body(appointments);
    }

    @GetMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAppointments(){
        logger.info("Get all appointments");
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for(Appointment a: appointments){
            appointmentDTOList.add(AppointmentDTO.from(a));
        }
        return ResponseEntity.accepted().body(appointmentDTOList);
    }

    @PostMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO){
        logger.info("Create new Appointment with payload. {}", appointmentDTO.toString());
        Appointment appointment = Appointment.builder()
                .description(appointmentDTO.getDescription())
                .dateTime(appointmentDTO.getDateTime())
                .created(ZonedDateTime.now())
                .updated(ZonedDateTime.now())
                .build();
        Appointment newAppointment = appointmentRepository.save(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("appointment/{id}").buildAndExpand(newAppointment.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/appointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAppointment(@PathVariable UUID id, @Valid @RequestBody AppointmentDTO appointmentDTO){
        logger.info("Update appointment with payload. {}", appointmentDTO.toString());
        Optional<Appointment> ownedAppointment = appointmentRepository.findById(id);
        if(ownedAppointment.isPresent()){
            Appointment updatedAppointment = Appointment.builder()
                    .id(id)
                    .description(appointmentDTO.getDescription())
                    .dateTime(appointmentDTO.getDateTime())
                    .created(ownedAppointment.get().getCreated())
                    .updated(ZonedDateTime.now())
                    .build();
            appointmentRepository.save(updatedAppointment);
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.badRequest().body("wrong");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
        logger.warn("Bad request received. Sending Response with errors");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
