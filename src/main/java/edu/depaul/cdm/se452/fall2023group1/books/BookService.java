package edu.depaul.cdm.se452.fall2023group1.books;

import lombok.extern.log4j.Log4j2;
import lombok.SneakyThrows;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookService {

    @Autowired
    private BookRepository repository;

    @SneakyThrows
    public List<Book> list() {
        log.info("Entering the list method.");
        try {
            var retval = repository.findAll();
            log.info("Exiting the list method successfully.");
            return retval;
        } catch (Exception e) {
            log.error("Exception caught in list method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Book add(Book book) {
        log.info("Entering the add method.");
        try {
            var savedBook = repository.save(book);
            log.info("Exiting the add method successfully.");
            return savedBook;
        } catch (Exception e) {
            log.error("Exception caught in add method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Book update(Long id, Book book) {
        log.info("Entering the update method.");
        try {
            if (!repository.existsById(id)) {
                throw new BookNotFoundException(id);
            }
            book.setBook_id(id);
            var updatedBook = repository.save(book);
            log.info("Exiting the update method successfully.");
            return updatedBook;
        } catch (Exception e) {
            log.error("Exception caught in update method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public void delete(Long id) {
        log.info("Entering the delete method.");
        try {
            if (!repository.existsById(id)) {
                throw new BookNotFoundException(id);
            }
            repository.deleteById(id);
            log.info("Exiting the delete method successfully.");
        } catch (Exception e) {
            log.error("Exception caught in delete method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Optional<Book> findById(Long id) {
        log.info("Entering the findById method.");
        try {
            var book = repository.findById(id);
            log.info("Exiting the findById method successfully.");
            return book;
        } catch (Exception e) {
            log.error("Exception caught in findById method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Page<Book> findByTitle(String title, Pageable pageable) {
        log.info("Entering the findByTitle method.");
        try {
            var books = repository.findByTitle(title, pageable);
            log.info("Exiting the findByTitle method successfully.");
            return books;
        } catch (Exception e) {
            log.error("Exception caught in findByTitle method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Page<Book> findByAuthor(String author, Pageable pageable) {
        log.info("Entering the findByAuthor method.");
        try {
            var books = repository.findByAuthor(author, pageable);
            log.info("Exiting the findByAuthor method successfully.");
            return books;
        } catch (Exception e) {
            log.error("Exception caught in findByAuthor method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Page<Book> findByGenre(String genre, Pageable pageable) {
        log.info("Entering the findByGenre method.");
        try {
            var books = repository.findByGenre(genre, pageable);
            log.info("Exiting the findByGenre method successfully.");
            return books;
        } catch (Exception e) {
            log.error("Exception caught in findByGenre method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Page<Book> findByPublicationYear(int year, Pageable pageable) {
        log.info("Entering the findByPublicationYear method.");
        try {
            var books = repository.findByPublicationYear(year, pageable);
            log.info("Exiting the findByPublicationYear method successfully.");
            return books;
        } catch (Exception e) {
            log.error("Exception caught in findByPublicationYear method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Book findByISBN(String isbn) {
        log.info("Entering the findByISBN method.");
        try {
            var book = repository.findByISBN(isbn);
            log.info("Exiting the findByISBN method successfully.");
            return book;
        } catch (Exception e) {
            log.error("Exception caught in findByISBN method: " + e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public List<Book> findBooksByGlobalRating(double rating) {
        log.info("Entering the findBooksByGlobalRating method.");
        try {
            var books = repository.findBooksByGlobalRating(rating);
            log.info("Exiting the findBooksByGlobalRating method successfully.");
            return books;
        } catch (Exception e) {
            log.error("Exception caught in findBooksByGlobalRating method: " + e.getMessage());
            throw e;
        }
    }

}
