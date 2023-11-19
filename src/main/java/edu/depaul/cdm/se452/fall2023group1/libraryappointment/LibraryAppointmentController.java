package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/libraryappointment")
@Log4j2
public class LibraryAppointmentController {

    @Autowired
    private LibraryAppointmentService libraryAppointmentService;

    // Create a new appointment
    @PostMapping
    public ResponseEntity<LibraryAppointment> createAppointment(@RequestBody LibraryAppointment appointment) {
        LibraryAppointment savedAppointment = libraryAppointmentService.makeReservation(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    // Get all appointments
    @GetMapping
    public ResponseEntity<List<LibraryAppointment>> getAllAppointments() {
        return ResponseEntity.ok(libraryAppointmentService.getAllAppointments());
    }

    // Update an existing appointment
    @PutMapping("/{id}")
    public ResponseEntity<LibraryAppointment> updateAppointment(@PathVariable Long id, @RequestBody LibraryAppointment appointmentDetails) {
        try {
            LibraryAppointment updatedAppointment = libraryAppointmentService.updateAppointment(id, appointmentDetails);
            return ResponseEntity.ok(updatedAppointment);
        } catch (LibraryAppointmentException e) {
            log.error("Error updating appointment: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            libraryAppointmentService.deleteAppointment(id);
            return ResponseEntity.ok().build();
        } catch (LibraryAppointmentException e) {
            log.error("Error deleting appointment: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
