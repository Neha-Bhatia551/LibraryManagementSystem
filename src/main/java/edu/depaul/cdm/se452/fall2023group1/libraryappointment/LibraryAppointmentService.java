package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


}
