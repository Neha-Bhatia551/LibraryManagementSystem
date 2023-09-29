package edu.depaul.cdm.se452.fall2023group1.bookreservations;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookreservations")
@Log4j2
public class BookReservationController {
    @GetMapping("/")
    public String list() {
        log.info("get bookreservations");
        return "Hello! this is  reservations";
    }


}
