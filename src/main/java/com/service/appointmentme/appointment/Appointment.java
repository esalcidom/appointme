package com.service.appointmentme.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Appointment {

    @Id
    private String id;
    private String description;
    private ZonedDateTime dateTime;
    private ZonedDateTime created;
    private ZonedDateTime updated;

}
