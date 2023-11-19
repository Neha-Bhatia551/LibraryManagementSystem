package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class LibraryAppointment {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_appointment_seq_gen")
    @SequenceGenerator(name = "library_appointment_seq_gen", sequenceName = "library_appointment_seq", initialValue = 5, allocationSize = 1)
    private Long id;
    private Long userId;
    @NotNull(message = "Appointment type cannot be null")
    private String appointmentType;
    private String timeSlot;
}
