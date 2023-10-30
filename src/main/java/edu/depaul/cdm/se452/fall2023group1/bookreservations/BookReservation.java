package edu.depaul.cdm.se452.fall2023group1.bookreservations;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import jakarta.persistence.*;

import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="bookreservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReservation {
    //use uuid for primary key??
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private long reservationId;

    @Column(name="book_id")
    private long bookId;
    //TODO: check issues with below
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Book book;

    @Column(name="user_id")
    private int userId;

    @Column(name="borrow_date")
    //@NotNull(message = "borrow date cannot be null/blank")
    private Timestamp borrowDate;
    //@NotNull(message = "return date cannot be null/blank")
    @Column(name="return_date")
    private Timestamp returnDate;
    @Enumerated(EnumType.STRING)
    private ReservationType type;

    @Column(name="checked_out")
    private Boolean checkedOut;
}
