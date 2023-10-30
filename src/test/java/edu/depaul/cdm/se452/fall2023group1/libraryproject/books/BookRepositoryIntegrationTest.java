package edu.depaul.cdm.se452.fall2023group1.libraryproject.books;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.depaul.cdm.se452.fall2023group1.books.BookStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(BookRepositoryIntegrationTest.class);
    @Autowired
    private BookRepository repository;
    private Book book = createSampleBook();

    @Test
    @Order(1)
    public void testAddBook() {
        repository.save(book);
        Optional<Book> found = repository.findById(book.getBook_id());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getTitle()).isEqualTo("Test Book");
    }

    @Test
    @Order(2)
    public void testUpdateBook() {
        // Create a new book and save it to the repository
        Book newBook = new Book();
        newBook.setTitle("Original Title");
        newBook.setAuthor("Original Author");
        newBook.setISBN("123-456-789");
        // ... set other fields ...
        repository.save(newBook);

        // Fetch the saved book to get its generated ID
        Long savedBookId = newBook.getBook_id();
        assertThat(savedBookId).isNotNull();  // Ensure the ID is generated

        // Update the title of the book
        newBook.setTitle("Updated Book");

        // Save the updated book to the repository
        repository.save(newBook);

        // Fetch the updated book from the repository
        Optional<Book> found = repository.findById(savedBookId);

        // Assert that the updated book exists and the title has been updated
        assertThat(found).isNotEmpty();
        assertThat(found.get().getTitle()).isEqualTo("Updated Book");
    }


    @Test
    @Order(3)
    public void testDeleteBook() {
        book = new Book();
        book.setTitle("Original Title");
        book.setAuthor("Original Author");
        book.setISBN("123-456-7891");
        repository.save(book);
        assertNotNull(book.getBook_id());

        // Delete the book and verify it was deleted
        repository.deleteById(book.getBook_id());
        Optional<Book> found = repository.findById(book.getBook_id());
        assertThat(found).isEmpty();
    }

    @Test
    @Order(4)
    public void testFindNonExistentBook() {
        Optional<Book> found = repository.findById(999L);
        assertThat(found).isEmpty();
    }

    @Test
    @Order(5)
    public void testFindByISBN() {
        Book found = repository.findByISBN("123-456");
        assertThat(found).isNotNull();
        assertThat(found.getISBN()).isEqualTo("123-456");
    }

    @Test
    @Order(6)
    public void testFindByAuthor() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = repository.findByAuthor("Test Author", pageable);
        assertThat(page).isNotEmpty();
        assertThat(page.getContent().get(0).getAuthor()).isEqualTo("Test Author");
    }

    @Test
    @Order(7)
    public void testFindByGenre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = repository.findByGenre("Test Genre", pageable);
        assertThat(page).isNotEmpty();
        assertThat(page.getContent().get(0).getGenre()).isEqualTo("Test Genre");
    }

    @Test
    @Order(8)
    public void testFindByTitle() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = repository.findByTitle("Updated Book", pageable);
        assertThat(page).isNotEmpty();
        assertThat(page.getContent().get(0).getTitle()).isEqualTo("Updated Book");
    }

    @Test
    @Order(9)
    public void testFindByPublicationYear() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = repository.findByPublicationYear(2020, pageable);
        assertThat(page).isNotEmpty();
        assertThat(page.getContent().get(0).getPublicationYear()).isEqualTo(2020);
    }

    private Book createSampleBook() {
        return Book.builder()
                .title("Test Book")
                .author("Test Author")
                .ISBN("123-456")
                .publicationYear(2020)
                .genre("Test Genre")
                .description("Test Description")
                .bookCount(5)
                .status(BookStatus.AVAILABLE)
                .globalRating(4.5)
                .build();
    }
}
