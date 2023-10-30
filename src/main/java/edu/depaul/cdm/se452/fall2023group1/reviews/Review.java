package edu.depaul.cdm.se452.fall2023group1.reviews;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NonNull
    private Long reviewId;

    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @Column(name = "book_id")
    @NonNull
    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "ISBN")
    @NonNull
    private Book book;

    @Column(name = "stars")
    @NonNull
    private Integer stars;

    @Column(name = "description")
    @NonNull
    private String description;
}
