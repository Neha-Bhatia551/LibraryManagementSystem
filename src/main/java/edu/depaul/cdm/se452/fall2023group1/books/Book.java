
package edu.depaul.cdm.se452.fall2023group1.books;

import jakarta.persistence.*;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true)
    private String ISBN;
    @Column()
    private String title;
    @Column()
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
    private String status;
    @Column()
    private double bookRating;


}
