
package edu.depaul.cdm.se452.fall2023group1.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> list() {

        log.traceEntry("Enter list");

        var retval = repository.findAll();
        log.traceExit("Exit list", retval);

        return repository.findAll();
    }

    public Book add(Book book) {
        log.traceEntry("enter save", book);
        repository.save(book);
        log.traceEntry("exit save", book);
        return repository.save(book);

    }


    public Book update(Long id, Book book) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        book.setId(id);
        return repository.save(book);
    }
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }
}
