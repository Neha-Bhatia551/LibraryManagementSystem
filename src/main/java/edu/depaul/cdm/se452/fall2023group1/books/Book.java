package edu.depaul.cdm.se452.fall2023group1.books;
/**
 * Represents the book in the library.
 * It includes fields which are  essential for book information such as ISBN, title, author, etc.
 * */
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservation;
import edu.depaul.cdm.se452.fall2023group1.reviews.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    /**
     * Unique identifier for the book.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(unique = true)
    @NotNull(message = "ISBN cannot be null")
    private String ISBN;

    @Column()
    @NotNull(message = "Title cannot be null")
    private String title;

    @Column()
    @NotNull(message = "Author cannot be null")
    private String author;

    @Column()
    private Integer publicationYear;

    @Column()
    // @NotNull(message = "genre cannot be null")
    private String genre;

    @Column()
    private String description;

    @Column()
    private Integer bookCount;

    @Column()
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column()
    private double globalRating;

    // Todo work with the team
    // @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    // private BookReservation bookReservation;

    // Todo work with the team
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<Review> reviews;

}
