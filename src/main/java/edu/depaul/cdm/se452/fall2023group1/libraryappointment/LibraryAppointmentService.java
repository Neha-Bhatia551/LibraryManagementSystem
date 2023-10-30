package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import org.springframework.beans.factory.annotation.Autowired;
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
        return libraryAppointmentRepository.save(appointment);
    }

    // handle computer reservations
    public LibraryAppointment reserveComputer(LibraryAppointment appointment) {
        logger.info("Reserving a computer");
        return libraryAppointmentRepository.save(appointment);
    }

    // handle book reservations
    public LibraryAppointment reserveBook(LibraryAppointment appointment) {
        logger.info("Reserving a book");
        return libraryAppointmentRepository.save(appointment);
    }

    // get all existing appointments
    public List<LibraryAppointment> getAllAppointments() {
        logger.info("Getting all appointments");
        return libraryAppointmentRepository.findAll();
    }
}
