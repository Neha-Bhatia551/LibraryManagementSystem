package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findByUserId(int id);
    //TODO: check how to get below finder working
    List<BookReservation> findByBookId(int id);

}
