package edu.depaul.cdm.se452.fall2023group1.libraryproject.bookreservations;

import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservation;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservationRepository;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.ReservationType;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookRepository;
import edu.depaul.cdm.se452.fall2023group1.user.User;
import edu.depaul.cdm.se452.fall2023group1.user.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class BookReservationRepositoryTest {

    @Autowired
    private BookReservationRepository bookReservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(0)
    @Commit
    public void addReservation() {
        var beforeCount = (int) bookReservationRepository.count();
        Optional<Book> book = bookRepository.findById(1L);
        Optional<User> user = userRepository.findById(2L);
        BookReservation reservation = new BookReservation();
        reservation.setBook(book.get());
        reservation.setType(ReservationType.DIGITAL);
        reservation.setUser(user.get());
        bookReservationRepository.save(reservation);
        var afterCount = (int) bookReservationRepository.count();
        assertEquals(afterCount , beforeCount + 1);
    }

    @Test
    @Commit
    @Order(1)
    public void getAllReservations() {
        List<BookReservation> reservation = bookReservationRepository.findAll();
        assertEquals(reservation.size(), 3);
    }

//    @Test
//    @Order(1)
//    public void getReservation() {
//        Optional<Book> book = bookRepository.findById(1L);
//        Optional<User> user = userRepository.findById(2L);
//        BookReservation reservation = new BookReservation();
//        reservation.setBook(book.get());
//        reservation.setType(ReservationType.DIGITAL);
//        reservation.setUser(user.get());
//        bookReservationRepository.save(reservation);
//        Optional<BookReservation> optreservation = bookReservationRepository.findById(1L);
//        assertEquals(optreservation.isPresent(), true);
//    }

//    @Test
//    @Order(2)
//    public void getReservationByUserId() {
//        var beforeCount = (int) bookReservationRepository.count();
//        List<BookReservation> reservation = bookReservationRepository.findByUserId(2);
//        assertEquals(reservation.size(), 2);
//    }
//
//    @Test
//    @Order(3)
//    public void getReservationByBookId() {
//        var beforeCount = (int) bookReservationRepository.count();
//        List<BookReservation> reservation = bookReservationRepository.findByBookId(1);
//        assertEquals(reservation.size(), 2  );
//    }
//
//    @Test
//    @Order(4)
//    public void updateReservation() {
//        Optional<BookReservation> reservation = bookReservationRepository.findById(1L);
//        long bookid_before = reservation.get().getBook().getBook_id();
//        BookReservation res = reservation.get();
//        Optional<Book> book = bookRepository.findById(2L);
//        res.setBook(book.get());
//        bookReservationRepository.save(res);
//        Optional<BookReservation> reservationafter = bookReservationRepository.findById(1L);
//        long bookid_after = reservation.get().getBook().getBook_id();
//        assertEquals(bookid_before ,1);
//        assertEquals(bookid_after, 2);
//
//    }
//
//    @Test
//    @Order(5)
//    public void deleteReservation() {
//        var beforeCount = (int) bookReservationRepository.count();
//        bookReservationRepository.deleteById(1L);
//        var afterCount = (int) bookReservationRepository.count();
//        assertEquals(afterCount, beforeCount - 1);
//    }

}
