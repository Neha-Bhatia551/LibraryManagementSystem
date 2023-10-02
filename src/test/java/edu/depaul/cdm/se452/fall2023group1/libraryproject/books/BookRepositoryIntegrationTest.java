package edu.depaul.cdm.se452.fall2023group1.libraryproject.books;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.depaul.cdm.se452.fall2023group1.libraryproject.books.BookControllerTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookRepository;
import edu.depaul.cdm.se452.fall2023group1.books.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);
    Book book = createSampleBook();
    @Autowired
    private BookRepository repository;


    @Test
    @Order(1)
    public void testAddBook() {

        repository.save(book);
        logger.info("Testing book id {}",book.getId());
        Book found = repository.findById(book.getId()).orElse(null);
        logger.info("Testing book id {}",found.getBookCount());
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Test Book");
    }
// should do some work on these
//    @Test
//    public void testUpdateBook() {
//        book.setTitle("updated book");
//        repository.save(book);
//        Book found = repository.findById(book.getId()).orElse(null);
//        assertThat(found).isNotNull();
//        assertThat(found.getTitle()).isEqualTo("updated book");
//    }

    //    @Test
//    public void testDeleteBook() {
//        logger.info("Testing book id {}",book.getId());
//
//        repository.deleteById(book.getId());
//        Optional<Book> found = repository.findById(book.getId());
//        assertThat(found).isEmpty();
//    }
    @Test
    public void testFindNonExistentBook() {
        Optional<Book> found = repository.findById(999L);
        assertThat(found).isEmpty();
    }
    @Test
    public void testFindByISBN() {

        Book found = repository.findByISBN("123-456");
        assertThat(found).isNotNull();
        assertThat(found.getISBN()).isEqualTo("123-456");
    }
    @Test
    public void testExceptionForNonExistentBook() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            Book book = repository.findById(999L).orElseThrow(() -> new BookNotFoundException(999L));
        });
        assertThat(exception.getMessage()).isEqualTo("Book id not found : 999");
    }
    @Test
    public void testFindByAuthor() {
        List<Book> found = repository.findByAuthor("Test Author");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void testFindByGenre() {

        List<Book> found = repository.findByGenre("Test Genre");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getGenre()).isEqualTo("Test Genre");
    }
    @Test
    public void testFindByTitle() {

        List<Book> found = repository.findByTitle("Test Book");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getTitle()).isEqualTo("Test Book");
    }
    @Test
    public void testFindByPublicationYear() {
        List<Book> found = repository.findByPublicationYear(2020);
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getPublicationYear()).isEqualTo(2020);
    }

    //use builder
    private Book createSampleBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setISBN("123-456");
        book.setPublicationYear(2020);
        book.setGenre("Test Genre");
        book.setDescription("Test Description");
        book.setBookCount(5);
        book.setStatus("Available");
        book.setBookRating(4.5);
        return book;
    }
}
