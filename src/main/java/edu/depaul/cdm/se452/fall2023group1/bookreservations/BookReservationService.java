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
        List<BookReservation> allReservations = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        return allReservations;
    }

    public BookReservation getReservationById(long id) {
        Optional<BookReservation> reservationById = repo.findById(id);
        return reservationById.orElse(null);
    }

    public List<BookReservation> getReservationsByUserId(int id) {
        List<BookReservation> reservations = StreamSupport.stream(repo.findByUserId(id).spliterator(), false).collect(Collectors.toList());
        return reservations;
    }

    public List<BookReservation> getReservationsByBookId(int id) {
        List<BookReservation> reservations = StreamSupport.stream(repo.findByBookId(id).spliterator(), false).collect(Collectors.toList());
        return reservations;
    }


    public BookReservation save(BookReservation reservation) {
        repo.save(reservation);
        return reservation;
    }

    public void deleteReservation(long id) {
        repo.deleteById(id);
    }


    //TODO: implement update return timestamp of a reservation based on id, user id and book id
}
