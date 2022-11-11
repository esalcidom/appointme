package com.service.appointmentme.appointment;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @NotNull
    @NotBlank
    @Size(min=1, max=30, message="Description should be between 1 and 30 characters long" )
    private String description;
    @NotBlank
//    @NotNull
    private ZonedDateTime dateTime;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public void validateTimeData(){
        if(dateTime == null){
            dateTime = ZonedDateTime.now();
        }
        if(created == null){
            created = ZonedDateTime.now();
        }
        if(updated == null){
            updated = ZonedDateTime.now();
        }
    }

}
