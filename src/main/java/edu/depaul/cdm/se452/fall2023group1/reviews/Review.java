package edu.depaul.cdm.se452.fall2023group1.reviews;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Data
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor(force = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @Column(name = "book_id")
    @NonNull
    private Long bookId;

    @NonNull
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "stars")
    @NonNull
    private Integer stars;

    @Column(name = "description")
    @NonNull
    private String description;
}