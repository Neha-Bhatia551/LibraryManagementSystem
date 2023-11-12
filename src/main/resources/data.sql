INSERT INTO books (title, author, ISBN, publication_year, genre, description, book_count, status,global_rating)
 VALUES('Test Book 1', 'Test Author 1', '123-456-234', 2022, 'Test Genre', 'Test Description2', 5, 'UNAVAILABLE', 4.5),
 ('Another Test Book', 'Another Author', '789-101', 2021, 'Another Genre', 'Another Description', 2, 'AVAILABLE', 3.5);

INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (123, 'Zaeem', 'Khan', 'hello@gmail.com', 'borrower');
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (456, 'Zaeem', 'Khan', 'world@gmail.com', 'librarian');

INSERT INTO reviews (user_id, book_id, stars, description) VALUES (123, 223, 5, 'pretty good book. I wish I actually opened it');
INSERT INTO reviews (user_id, book_id, stars, description) VALUES (123, 1234, 4, 'a short description for book 1234');
INSERT INTO reviews (user_id, book_id, stars, description) VALUES (456, 223, 3, 'another review for book 223');

INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type, checked_out) VALUES (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'DIGITAL', true);
INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type, checked_out) VALUES (2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PHYSICAL', false);