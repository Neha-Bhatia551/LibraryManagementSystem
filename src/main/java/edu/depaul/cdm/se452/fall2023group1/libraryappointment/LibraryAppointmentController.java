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
    private LibraryAppointmentService libraryAppointmentService;

    @PostMapping("/makeReservation")
    public LibraryAppointment makeReservation(@RequestBody LibraryAppointment appointment) {
        return libraryAppointmentService.makeReservation(appointment);
    }

    @PostMapping("/reserveComputer")
    public LibraryAppointment reserveComputer(@RequestBody LibraryAppointment appointment) {
        return libraryAppointmentService.reserveComputer(appointment);
    }

    @PostMapping("/reserveBook")
    public LibraryAppointment reserveBook(@RequestBody LibraryAppointment appointment) {
        return libraryAppointmentService.reserveBook(appointment);
    }

    @GetMapping("/getAllAppointments")
    public List<LibraryAppointment> getAllAppointments() {
        return libraryAppointmentService.getAllAppointments();
    }
}
