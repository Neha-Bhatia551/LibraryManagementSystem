package edu.depaul.cdm.se452.fall2023group1.reviews;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

@Entity
@Table(name = "review")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Review {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @NonNull
    private Integer reviewId;

    @Column(name = "author")
    @NonNull
    private Integer userId;

    @Column(name = "isbn")
    @NonNull
    private String isbn;

    @Column(name = "stars")
    @NonNull
    private Integer stars;

    @Column(name = "description")
    @NonNull
    private String description;
}
