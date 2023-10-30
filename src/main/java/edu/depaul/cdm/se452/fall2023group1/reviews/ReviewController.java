package edu.depaul.cdm.se452.fall2023group1.reviews;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Log4j2
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) throws ReviewNotFoundException{
        try {
            Review review = reviewService.getReview(id);
            return ResponseEntity.ok(review);
        }
        catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok().body(reviewService.saveOrUpdate(id, review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok().body(reviewService.saveOrUpdate(id, review));
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) throws ReviewNotFoundException{
        reviewService.delete(id);
        // Todo: return ResponseEntity
    }

    @GetMapping("/book")
    public ResponseEntity<?> getReviewsByBookId(@RequestParam() Long id, @RequestParam(required = false) Integer stars) throws ReviewNotFoundException {
//        public ResponseEntity<?> getReviewsByBookId(@RequestParam() Long id) throws ReviewNotFoundException {
        try {
//            Integer stars = null;
            if (stars == null) {
                List<Review> reviews = reviewService.getReviewsByBook(id);
                return ResponseEntity.ok(reviews);
            }
            else {
                List<Review> reviews = reviewService.getReviewsByBookAndRating(id, stars);
                return ResponseEntity.ok(reviews);
            }
        }
        catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/author")
    public ResponseEntity<?> getReviewsByUserId(@RequestParam Long user) throws ReviewNotFoundException{
        try {
            List<Review> reviews = reviewService.getReviewsByUserId(user);
            return ResponseEntity.ok(reviews);
        }
        catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
