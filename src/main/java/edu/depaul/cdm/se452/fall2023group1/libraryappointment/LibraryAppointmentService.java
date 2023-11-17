package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LibraryAppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryAppointmentService.class);

    @Autowired
    private LibraryAppointmentRepository libraryAppointmentRepository;

    // handle general reservations
    public LibraryAppointment makeReservation(LibraryAppointment appointment) {
        logger.info("Making a reservation");
        if (appointment.getAppointmentType() == null) {
            throw new LibraryAppointmentException("Appointment type cannot be null");
        }
        if (isOverlappingAppointment(appointment)) {
            throw new LibraryAppointmentException("Appointment time conflicts with an existing appointment.");
        }
        return libraryAppointmentRepository.save(appointment);
    }

    // handle computer reservations
    public LibraryAppointment reserveComputer(LibraryAppointment appointment) {
        logger.info("Reserving a computer");
        if (appointment.getAppointmentType() == null) {
            throw new LibraryAppointmentException("Appointment type cannot be null");
        }
        return libraryAppointmentRepository.save(appointment);
    }

    // handle book reservations
    public LibraryAppointment reserveBook(LibraryAppointment appointment) {
        logger.info("Reserving a book");
        if (appointment.getAppointmentType() == null) {
            throw new LibraryAppointmentException("Appointment type cannot be null");
        }
        return libraryAppointmentRepository.save(appointment);
    }

    // get all existing appointments
    public List<LibraryAppointment> getAllAppointments() {
        logger.info("Getting all appointments");
        return libraryAppointmentRepository.findAll();
    }

    // Get appointment by ID
    public LibraryAppointment getAppointment(Long id) {
        logger.info("Retrieving appointment with ID: {}", id);
        return libraryAppointmentRepository.findById(id)
                .orElseThrow(() -> new LibraryAppointmentException("Appointment not found with ID: " + id));
    }

    // Delete appointment by ID
    public void deleteAppointment(Long id) {
        logger.info("Deleting appointment with ID: {}", id);
        try {
            libraryAppointmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LibraryAppointmentException("Appointment not found with ID: " + id);
        }
    }

    // Update appointment by ID:
    public LibraryAppointment updateAppointment(Long id, LibraryAppointment updatedAppointment) {
        logger.info("Updating appointment with ID: {}", id);
        return libraryAppointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setUserId(updatedAppointment.getUserId());
                    appointment.setAppointmentType(updatedAppointment.getAppointmentType());
                    appointment.setTimeSlot(updatedAppointment.getTimeSlot());
                    return libraryAppointmentRepository.save(appointment);
                }).orElseThrow(() -> new LibraryAppointmentException("Appointment not found with ID: " + id));
    }

    public boolean isOverlappingAppointment(LibraryAppointment newAppointment) {
        List<LibraryAppointment> existingAppointments = libraryAppointmentRepository.findByAppointmentTypeAndTimeSlot(
                newAppointment.getAppointmentType(), newAppointment.getTimeSlot());

        // Parse the time slot of the new appointment
        String[] newTimeSlotParts = newAppointment.getTimeSlot().split(" - ");
        LocalDateTime newStart = LocalDateTime.parse(newTimeSlotParts[0], DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        LocalDateTime newEnd = LocalDateTime.parse(newTimeSlotParts[1], DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));

        for (LibraryAppointment existingAppointment : existingAppointments) {
            // Parse the time slot of the existing appointment
            String[] existingTimeSlotParts = existingAppointment.getTimeSlot().split(" - ");
            LocalDateTime existingStart = LocalDateTime.parse(existingTimeSlotParts[0], DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
            LocalDateTime existingEnd = LocalDateTime.parse(existingTimeSlotParts[1], DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));

            // Check for overlap
            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                return true; // Overlap found
            }
        }

        return false; // No overlap found
    }
}
