package edu.depaul.cdm.se452.fall2023group1.libraryproject.books;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookController;
import edu.depaul.cdm.se452.fall2023group1.books.BookNotFoundException;
import edu.depaul.cdm.se452.fall2023group1.books.BookService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class BookControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Test
    public void testListBooks() {
        Book book1 = new Book(1L, "9739905387948", "OLqkftPAIe", "swzSaDXavM", 1925, "ZCzdQILBpi", "description1", 3, "Checked Out", 3.2);
        Book book2 = new Book(2L, "6673131741758", "ANNeEAVxDs", "elJMKUMWPX", 1906, "TgIPcDACGf", "description2", 17, "Available", 3.3);
        Book book3 = new Book(3L, "3230651772286", "UzBZaJagoM", "ToNzkzJWop", 1952, "aLVguUaPTj", "description3", 12, "Checked Out", 3.6);
        Book book4 = new Book(4L, "2888594110650", "AXPEcxvRHs", "kBmdkNTFlI", 1938, "AVzoswkfIK", "description4", 5, "Available", 0.8);
        Book book5 = new Book(5L, "4702631746185", "NpJOHezqux", "loQVoCrwjU", 1920, "cLVziRusdW", "description5", 12, "Available", 2.8);

        List<Book> mockBooks = Arrays.asList(book1, book2, book3, book4, book5);

        when(bookService.list()).thenReturn(mockBooks);
        List<Book> result = bookController.list();
        logger.info("Returned number of books: {}", result.size());
        assertEquals(5, result.size());
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        when(bookService.add(any(Book.class))).thenReturn(book);
        Book addedBook = bookController.add(new Book());
        assertEquals(1L, addedBook.getId());
        assertEquals("Test Book", addedBook.getTitle());
    }

    @Test
    public void testUpdateNonExistentBook() {
        Book book = new Book();
        book.setId(99L);
        when(bookService.update(any(Long.class), any(Book.class))).thenThrow(new BookNotFoundException(99L));
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookController.update(99L, book));
        logger.info("Exception message: {}", exception.getMessage());
        assertEquals("Book id not found : 99", exception.getMessage());
    }
}
