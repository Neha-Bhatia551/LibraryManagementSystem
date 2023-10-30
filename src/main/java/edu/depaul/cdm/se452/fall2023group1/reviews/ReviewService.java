package edu.depaul.cdm.se452.fall2023group1.reviews;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookNotFoundException;
import edu.depaul.cdm.se452.fall2023group1.books.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ReviewService {
    @Autowired
    private ReviewRepository reviews;

    @Autowired
    private BookRepository books;


    public Review getReview(Long reviewId) {
        return reviews.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with ID " + reviewId + " not found"));
    }

    public Review saveOrUpdate(Long id, Review review) {
        // ToDo: search and verify id
        return reviews.save(review);
    }

    public void delete(Long reviewId) {

        if (!reviews.existsById(reviewId)) {
            throw new ReviewNotFoundException("Review with ID " + reviewId + " not found");
        }
        reviews.deleteById(reviewId);
    }

    public List<Review> getReviewsByBook(Long bookId) {
        List<Review> res =  reviews.findAll().stream().filter((r -> r.getBookId().equals(bookId))).collect(Collectors.toList());
        if (res.isEmpty()) {
            throw new ReviewNotFoundException("Reviews for book " + bookId + " not found");
        }
        return res;
    }

    public List<Review> getReviewsByBookAndRating(Long bookId, Integer stars) {
        List<Review> res = reviews.findAll().stream()
                .filter(r -> r.getBookId().equals(bookId))
                .filter(r -> r.getStars().equals(stars))
                .collect(Collectors.toList());

        if (res.isEmpty()) {
            throw new ReviewNotFoundException(stars + "-star reviews for book " + bookId + " not found");
        }
        return res;
    }

    public List<Review> getReviewsByUserId(Long userId) {
        List<Review> result = reviews.findAll().stream().filter((review -> review.getUserId().equals(userId))).toList();
        if (result.isEmpty()) {
            throw new ReviewNotFoundException("Reviews written by user  " + userId + " not found");
        }
        return result;
    }

}
