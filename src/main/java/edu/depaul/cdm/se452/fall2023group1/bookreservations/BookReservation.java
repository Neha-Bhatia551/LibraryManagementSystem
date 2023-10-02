package edu.depaul.cdm.se452.fall2023group1.bookreservations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name="user_id")
    private int userId;
    @Column(name="borrow_date")
    private String borrowDate;
    @Column(name="return_date")
    private String returnDate;
    private String type;
}