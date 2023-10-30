package edu.depaul.cdm.se452.fall2023group1.books;

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
@Table(name = "Books")
public class Book {
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

   @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
     private List<Review> reviews;

}
