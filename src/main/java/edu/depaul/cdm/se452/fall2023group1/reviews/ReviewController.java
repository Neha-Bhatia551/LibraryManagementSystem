package edu.depaul.cdm.se452.fall2023group1.reviews;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@Log4j2
public class ReviewController {

    @Autowired
    private ReviewRepository reviews;

    @GetMapping("/1")
    public ResponseEntity<Optional<Review>> getReviewTest() {
        Review r1 = new Review(1, 1, "978-0321573513", 5, "pretty good book. I wish I actually opened it");
        reviews.save(r1);
        return ResponseEntity.ok(reviews.findById(1));
    }

}
