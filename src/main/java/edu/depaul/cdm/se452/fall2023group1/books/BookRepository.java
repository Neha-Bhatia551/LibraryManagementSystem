
package edu.depaul.cdm.se452.fall2023group1.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

    // added few more finders

    List<Book> findByGenre(String genre);
    List<Book> findByPublicationYear(int year);
    Book findByISBN(String ISBN);
    Book findBooksByBookRating(double Rating);


}
