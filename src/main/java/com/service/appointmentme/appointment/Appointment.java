package com.service.appointmentme.appointment;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String description;
    private ZonedDateTime dateTime;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public void validateTimeData(){
        if(dateTime == null){
            dateTime = ZonedDateTime.now(ZoneOffset.UTC);
        }
        if(created == null){
            created = ZonedDateTime.now(ZoneOffset.UTC);
        }
        if(updated == null){
            updated = ZonedDateTime.now(ZoneOffset.UTC);
        }
    }

}
