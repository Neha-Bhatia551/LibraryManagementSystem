package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookService;
import edu.depaul.cdm.se452.fall2023group1.user.User;
import edu.depaul.cdm.se452.fall2023group1.user.UserRepository;
import edu.depaul.cdm.se452.fall2023group1.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/bookreservation")
public class BookReservationController {

    @Autowired
    private BookReservationService service;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("reservations", service.getAllReservations());
        model.addAttribute("books", bookService.list());
        model.addAttribute("users", userService.list());
        if (session.getAttribute("reservation") == null) {
            model.addAttribute("reservation", new BookReservation());
            model.addAttribute("btnAddOrModifyLabel", "Reserve Book");
        } else {
            model.addAttribute("reservation", session.getAttribute("reservation"));
            model.addAttribute("btnAddOrModifyLabel", "Modify Book");
        }
        return "bookreservations/list";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("reservation", service.getReservationById(id));
        return "redirect:/bookreservation";
    }

    @PostMapping
    public String save(@ModelAttribute BookReservation reservation, HttpSession session) {
        if (reservation.getReservationId() == 0) {
            java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
            reservation.setBorrowDate(now);
            reservation.setCheckedOut(true);
            service.save(reservation);
        } else {
            var editReservation = service.getReservationById(reservation.getReservationId());
            Optional<Book> book = bookService.findById(reservation.getBook().getBook_id());
            Optional<User> user = repository.findById(reservation.getUser().getId());
            editReservation.setBook(book.get());
            editReservation.setUser(user.get());
            editReservation.setReturnDate(reservation.getReturnDate());
            editReservation.setType(reservation.getType());
            service.save(editReservation);
            session.setAttribute("reservation", null);
        }
        return "redirect:/bookreservation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.deleteReservation(id);
        return "redirect:/bookreservation";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
