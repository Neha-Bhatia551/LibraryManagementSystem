package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Log4j2
public class BookReservationService {
    @Autowired
    private BookReservationRepository repo;

    public List<BookReservation> getAllReservations() {
        log.traceEntry("Enter getAllReservations");
        List<BookReservation> allReservations = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceEntry("Exit save", allReservations);
        return allReservations;
    }

    public BookReservation getReservationById(long id) {
        log.traceEntry("Enter getAllReservations");
        Optional<BookReservation> reservationById = repo.findById(id);
        log.traceEntry("Exit save");
        return reservationById.orElse(null);
    }

    public BookReservation save(BookReservation reservation) {
        log.traceEntry("Enter save: ", reservation);
        repo.save(reservation);
        log.traceEntry("Exit save", reservation);
        return reservation;
    }

    public void deleteReservation(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    //TODO: implement getreservation by id, user id and book id

    //TODO: implement update return timestamp of a reservation based on id, user id and book id
}
