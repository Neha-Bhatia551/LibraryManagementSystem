INSERT INTO books (title, author, ISBN, publication_year, genre, description, book_count, status,global_rating)
 VALUES('Test Book 1', 'Test Author 1', '123-456-234', 2022, 'Test Genre', 'Test Description2', 5, 'UNAVAILABLE', 4.5),
 ('Another Test Book', 'Another Author', '789-101', 2021, 'Another Genre', 'Another Description', 2, 'AVAILABLE', 3.5);

INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (123, 'Zaeem', 'Khan', 'hello@gmail.com', 'borrower');
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (456, 'Zaeem', 'Khan', 'world@gmail.com', 'librarian');

INSERT INTO reviews (user_id, book_id, stars, description) VALUES (123, 223, 5, 'pretty good book. I wish I actually opened it');
INSERT INTO reviews (user_id, book_id, stars, description) VALUES (123, 1234, 4, 'a short description for book 1234');
INSERT INTO reviews (user_id, book_id, stars, description) VALUES (456, 223, 3, 'another review for book 223');

INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type, checked_out) VALUES (1, 1, '2023-11-10', '2023-11-10', 'DIGITAL', true);
INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type, checked_out) VALUES (2, 2, '2023-11-10', '2023-11-10', 'PHYSICAL', false);

INSERT INTO library_appointment (id, user_id, appointment_type, time_slot)
VALUES
    (1, 123, 'General', '01/01/2023 10:00 - 01/01/2023 11:00'),
    (2, 123, 'Computer', '01/02/2023 11:00 - 01/02/2023 12:00'),
    (3, 456, 'Book', '01/03/2023 12:00 - 01/03/2023 13:00'),
    (4, 456, 'Study', '01/04/2023 13:00 - 01/04/2023 14:00');

--Neha UI test data
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (1, 'Neha', 'Bhatia', 'world@gmail.com', 'librarian');
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (2, 'Anu', 'Sharma', 'world@gmail.com', 'borrower');
INSERT INTO Users(user_id, first_name, last_name, email, role) VALUES (3, 'Ruchi', 'Mehta', 'world@gmail.com', 'borrower');

INSERT INTO books (title, author, ISBN, publication_year, genre, description, book_count, status,global_rating)
VALUES('The three things', 'John william', '123-356-234', 2022, 'Thriller', 'Test Description2', 5, 'AVAILABLE', 4.5),
      ('All I said', 'Blah', '789-131', 2021, 'Another Genre', 'Another Description', 2, 'AVAILABLE', 3.5);

INSERT INTO BOOKRESERVATIONS (book_id, user_id, borrow_date, return_date, type, checked_out) VALUES (3, 3, '2023-11-10', '2023-11-10', 'DIGITAL', true);
