package edu.depaul.cdm.se452.fall2023group1.libraryappointment;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LibraryAppointmentExceptionHandler {

    @ExceptionHandler(value = { LibraryAppointmentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleLibraryAppointmentException(LibraryAppointmentException ex) {
        return new ResponseEntity<>("Library appointment error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        // You can customize the response based on the violations
        return new ResponseEntity<>("Constraint violation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
