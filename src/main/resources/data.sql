INSERT INTO books (id, title, author, ISBN, publication_year, genre, description, book_count, status, book_rating)
VALUES(223,'Test Book 1', 'Test Author 1', '123-456-234', 2022, 'Test Genre', 'Test Description2', 5, 'Available', 4.5),
(1234,'Another Test Book', 'Another Author', '789-101', 2021, 'Another Genre', 'Another Description', 2, 'Available', 3.5);

INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (123, 'Zaeem', 'Khan', 'hello@gmail.com', 'borrower');
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (456, 'Zaeem', 'Khan', 'world@gmail.com', 'librarian');

INSERT INTO review (id, author, isbn, stars, description) VALUES (1, 1, '9780321573513', 5, 'pretty good book. I wish I actually opened it');
INSERT INTO review (id, author, isbn, stars, description) VALUES(2, 1, '9780593128508', 4, 'description 2');
INSERT INTO review (id, author, isbn, stars, description) VALUES(3, 123, '123-456-234', 3, 'some description 3');

INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type) VALUES (223, 123, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Digital');
INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type) VALUES (1234, 456, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Physical');
