package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
}
