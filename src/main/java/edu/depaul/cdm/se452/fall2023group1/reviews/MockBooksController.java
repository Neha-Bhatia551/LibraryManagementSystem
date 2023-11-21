package edu.depaul.cdm.se452.fall2023group1.reviews;

import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservation;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/mock/books")
public class MockBooksController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("books", bookService.list());
        return "reviews/mockListBooks";
    }

    @GetMapping("/{book}/reviews")
    public String review(@PathVariable Long book, Model model) {
        model.addAttribute("reviews", reviewService.getReviewsByBook(book));
        return "reviews/review";
    }

    @GetMapping("/{book}/new-review")
    public String reviewNew(@PathVariable Long book, Model model) {
        model.addAttribute("id", book);
        return "reviews/reviewNew";
    }


}
