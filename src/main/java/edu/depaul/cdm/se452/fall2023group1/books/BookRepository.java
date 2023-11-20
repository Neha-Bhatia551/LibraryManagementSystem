package edu.depaul.cdm.se452.fall2023group1.books;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByAuthor(String author, Pageable pageable);

    Page<Book> findByGenre(String genre, Pageable pageable);

    Page<Book> findByPublicationYear(int year, Pageable pageable);

    Page<Book> findByISBN(String isbn, Pageable pageable);

    List<Book> findBooksByGlobalRating(double Rating);

}
