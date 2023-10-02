package edu.depaul.cdm.se452.fall2023group1.books;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }
}
