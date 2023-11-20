package edu.depaul.cdm.se452.fall2023group1.bookreservations;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.user.User;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

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
