package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraryappointment")
@Log4j2
public class LibraryAppointmentController {

    @Autowired
    private LibraryAppointmentRepository repository;

    @PostMapping("/makeReservation")
    public LibraryAppointment makeReservation(@RequestBody LibraryAppointment appointment) {
        log.info("Making a reservation");
        return repository.save(appointment);
    }

    @PostMapping("/reserveComputer")
    public LibraryAppointment reserveComputer(@RequestBody LibraryAppointment appointment) {
        log.info("Reserving a computer");
        return repository.save(appointment);
    }

    @PostMapping("/reserveRareBooks")
    public LibraryAppointment reserveRareBooks(@RequestBody LibraryAppointment appointment) {
        log.info("Reserving rare books");
        return repository.save(appointment);
    }

    @GetMapping("/getAllAppointments")
    public List<LibraryAppointment> getAllAppointments() {
        log.info("Getting all appointments");
        return repository.findAll();
    }
}
