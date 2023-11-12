package edu.depaul.cdm.se452.fall2023group1.bookreservations;

public class BookReservationNotFoundException extends RuntimeException {
    public BookReservationNotFoundException(Long id) {
        super("Reservation id not found : " + id);
    }
}

