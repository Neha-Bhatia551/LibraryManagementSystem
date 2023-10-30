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


    @GetMapping
    @Operation(summary = "Returns all the reservations from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public List<BookReservation> list() {
        return service.getAllReservations();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Returns the reservation associated with that ID from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public BookReservation getBookReservationById(@PathVariable long id) {
        return service.getReservationById(id);
    }

    @PostMapping
    @Operation(summary = "Save the reservation and returns the reservation id")
    public ResponseEntity<String> save(@Valid @RequestBody BookReservation reservation) {
        service.save(reservation);
        return ResponseEntity.ok("New reservation id is " + reservation.getReservationId());
    }

    @DeleteMapping("delete/id/{id}")
    @Operation(summary = "Delete the reservation")
    public void delete(@PathVariable long id) {
        //TODO: check role of the user before deleting reservation
        service.deleteReservation(id);
    }

    @GetMapping("/userid/{userid}")
    @Operation(summary = "Returns the reservations associated with that user ID from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public List<BookReservation> getBookReservationsByUserId(@PathVariable int userid) {
        return service.getReservationsByUserId(userid);
    }

    @GetMapping("/bookid/{bookid}")
    @Operation(summary = "Returns the reservations associated with that book ID from the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=BookReservation.class))})
    public List<BookReservation> getBookReservationsByBookId(@PathVariable int bookid) {
        return service.getReservationsByBookId(bookid);
    }



    //TODO: implement update api to update return date of a reservation
    //TODO: handle exceptions


}
