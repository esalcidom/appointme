package com.service.appointmentme.appointment;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
public class AppointmentDTO {

    private UUID id;
    @NotNull
    @NotBlank
    @Size(min=1, max=30, message="Description should be between 1 and 30 characters long")
    private String description;
    @NotNull
    private ZonedDateTime dateTime;

    public static AppointmentDTO from(Appointment appointment){
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .id(appointment.getId())
                .description(appointment.getDescription())
                .dateTime(appointment.getDateTime())
                .build();
        return appointmentDTO;
    }

}
