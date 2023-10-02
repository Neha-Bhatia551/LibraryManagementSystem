package edu.depaul.cdm.se452.fall2023group1.bookreservations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@RestController
@RequestMapping("/api/bookreservations")
@Tag(name = "BookReservation", description = "All Book reservations")
@Log4j2
public class BookReservationController {

    @Autowired
    private BookReservationService service;

    //TODO: Handle if service returns null, show appropriate message to the user


    @GetMapping("/")
    @Operation(summary = "Returns all the reservations from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public List<BookReservation> list() {
        log.debug("Fetching bookreservations");
        return service.getAllReservations();
    }

    @GetMapping("/getReservationById/{id}")
    @Operation(summary = "Returns the reservation associated with that ID from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public BookReservation getBookReservationById(@PathVariable long id) {
        return service.getReservationById(id);
    }

    @PostMapping
    @Operation(summary = "Save the reservation and returns the reservation id")
    public long save(BookReservation reservation) {
        log.traceEntry("enter save", reservation);
        service.save(reservation);
        log.traceExit("exit save", reservation);
        return reservation.getReservationId();
    }

    @PostMapping("/validated")
    @Operation(summary = "Save the reservation and returns the reservation id")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody BookReservation reservation) {
        log.traceEntry("enter save", reservation);
        service.save(reservation);
        log.traceExit("exit save", reservation);
        return ResponseEntity.ok("new id is " + reservation.getReservationId());
    }

    @DeleteMapping
    @Operation(summary = "Delete the reservation")
    public void delete(long id) {
        //TODO: check role of the user before deleting reservation
        log.traceEntry("Enter delete", id);
        service.deleteReservation(id);
        log.traceExit("Exit delete");
    }

    //TODO: implement get apis to get reservations by user id and book id
    //TODO: implement update api to update return date of a reservation
    //    @GetMapping("/getReservationsByUser/{userId}")
//    public List<BookReservation> getBookReservationsById(@PathVariable int userId) {
//
//    }


}
