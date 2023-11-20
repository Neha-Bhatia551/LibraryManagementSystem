package edu.depaul.cdm.se452.fall2023group1.books;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/books")
@Tag(name = "book", description = "Get  all Book details")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/list")
    @Operation(summary = "Get a list of all books")
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) })
    public String list(Model model) {
        List<Book> books = service.list();
        model.addAttribute("books", books);
        return "listBooks";
    }

//    @PostMapping
//    @Operation(summary = "Add a new book")
//    @ApiResponse(responseCode = "201", description = "Book created", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) })
//    public Book add(@RequestBody Book book) {
//        return service.add(book);
//    }

//    @PutMapping("/{id}")
//    @Operation(summary = "Update book details by ID")
//    @ApiResponse(responseCode = "200", description = "Book updated", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) })
//    public Book update(@PathVariable Long id, @RequestBody Book book) {
//        return service.update(id, book);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book details by ID")
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) })
    public Optional<Book> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Get books by title")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public Page<Book> findByTitle(@PathVariable String title, Pageable pageable) {
        return service.findByTitle(title, pageable);
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Get books by author")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public Page<Book> findByAuthor(@PathVariable String author, Pageable pageable) {
        return service.findByAuthor(author, pageable);
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get books by genre")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public Page<Book> findByGenre(@PathVariable String genre, Pageable pageable) {
        return service.findByGenre(genre, pageable);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get books by publication year")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public Page<Book> findByPublicationYear(@PathVariable int year, Pageable pageable) {
        return service.findByPublicationYear(year, pageable);
    }

    @GetMapping("/rating/{rating}")
    @Operation(summary = "Get books by global rating")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) })
    public List<Book> findBooksByGlobalRating(@PathVariable double rating) {
        return service.findBooksByGlobalRating(rating);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Operation(summary = "Handle book not found exception")
    @ApiResponse(responseCode = "404", description = "Book not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) })
    public ResponseEntity<String> handleExceptions(BookNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search")
    public String searchBooks(@RequestParam String query,
                              @RequestParam String filterType,
                              Pageable pageable, Model model) {
        Page<Book> books =service.searchBooks(query, filterType, pageable);
        model.addAttribute("books", books);
        return "searchResults";
    }
    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }
    // Admin dashboard
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<Book> books = service.list();
        model.addAttribute("books", books);
        return "adminDashboard";
    }

    // Add a new book
    @GetMapping("/admin/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "adminAddBook";
    }


    @PostMapping("/admin/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttrs) {
        service.add(book);
        redirectAttrs.addFlashAttribute("message", "Book added successfully.");
        return "redirect:/api/books/admin";
    }

    // Update a book
    @GetMapping("/admin/update/{id}")
    public String showUpdateBookForm(@PathVariable Long id, Model model) {
        Book book = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "adminUpdateBook";
    }

    @PostMapping("/admin/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttrs) {
        service.update(id, book);
        redirectAttrs.addFlashAttribute("message", "Book updated successfully.");
        return "redirect:/api/books/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        service.delete(id);
        redirectAttrs.addFlashAttribute("message", "Book deleted successfully.");
        return "redirect:/api/books/admin";
    }


}