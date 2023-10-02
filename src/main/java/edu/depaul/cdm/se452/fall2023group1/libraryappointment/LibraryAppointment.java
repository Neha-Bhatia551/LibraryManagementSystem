package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class LibraryAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String appointmentType;
    private String timeSlot;
}
