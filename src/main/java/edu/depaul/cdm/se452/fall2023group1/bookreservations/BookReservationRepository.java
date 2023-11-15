package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    @Query(value = "SELECT * from BookReservations where user_id=?1" , nativeQuery = true)
    List<BookReservation> findByUserId(long id);

    @Query(value = "SELECT * from BookReservations where book_id=?1" , nativeQuery = true)
    List<BookReservation> findByBookId(long bookid);

}
