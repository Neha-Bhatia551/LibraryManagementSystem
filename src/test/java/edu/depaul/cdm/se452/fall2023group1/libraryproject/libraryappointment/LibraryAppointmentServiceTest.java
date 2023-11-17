package edu.depaul.cdm.se452.fall2023group1.libraryproject.libraryappointment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointment;
import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointmentException;
import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointmentRepository;
import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointmentService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class LibraryAppointmentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(LibraryAppointmentServiceTest.class);

    @Autowired
    private LibraryAppointmentService libraryAppointmentService;

    @MockBean
    private LibraryAppointmentRepository libraryAppointmentRepository;

    @Test
    public void testMakeReservation() {
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(1L);
        appointment.setAppointmentType("General");
        appointment.setTimeSlot("10:00 AM - 11:00 AM");

        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(appointment);
        LibraryAppointment savedAppointment = libraryAppointmentService.makeReservation(appointment);

        assertEquals("General", savedAppointment.getAppointmentType());
        logger.info("Reservation made for appointment type: {}", savedAppointment.getAppointmentType());
    }

    @Test
    public void testReserveComputer() {
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(1L);
        appointment.setAppointmentType("Computer");
        appointment.setTimeSlot("11:00 AM - 12:00 PM");

        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(appointment);
        LibraryAppointment savedAppointment = libraryAppointmentService.reserveComputer(appointment);

        assertEquals("Computer", savedAppointment.getAppointmentType());
    }

    @Test
    public void testReserveBook() {
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(1L);
        appointment.setAppointmentType("Book");
        appointment.setTimeSlot("12:00 PM - 01:00 PM");

        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(appointment);
        LibraryAppointment savedAppointment = libraryAppointmentService.reserveBook(appointment);

        assertEquals("Book", savedAppointment.getAppointmentType());
    }

    @Test
    public void testDeleteAppointment() {
        Long appointmentId = 1L; // Assuming this ID exists
        doNothing().when(libraryAppointmentRepository).deleteById(appointmentId);
        libraryAppointmentService.deleteAppointment(appointmentId);
        verify(libraryAppointmentRepository, times(1)).deleteById(appointmentId);
    }

    @Test
    public void testUpdateAppointment() {
        Long appointmentId = 1L; // Assuming this ID exists
        LibraryAppointment existingAppointment = new LibraryAppointment();
        existingAppointment.setId(appointmentId);
        existingAppointment.setUserId(1L);
        existingAppointment.setAppointmentType("General");
        existingAppointment.setTimeSlot("10:00 AM - 11:00 AM");

        LibraryAppointment updatedAppointment = new LibraryAppointment();
        updatedAppointment.setUserId(2L);
        updatedAppointment.setAppointmentType("Computer");
        updatedAppointment.setTimeSlot("11:00 AM - 12:00 PM");

        when(libraryAppointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(updatedAppointment);

        LibraryAppointment result = libraryAppointmentService.updateAppointment(appointmentId, updatedAppointment);

        assertEquals("Computer", result.getAppointmentType());
        assertEquals("11:00 AM - 12:00 PM", result.getTimeSlot());
    }

    // Test for making a reservation with missing mandatory fields
    @Test
    public void testMakeReservationWithMissingFields() {
        LibraryAppointment appointment = new LibraryAppointment();
        // Missing appointmentType
        appointment.setUserId(1L);
        appointment.setTimeSlot("10:00 AM - 11:00 AM");

        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.makeReservation(appointment));
    }

    // Test for updating a non-existent appointment
    @Test
    public void testUpdateNonExistentAppointment() {
        Long nonExistentId = 999L;
        LibraryAppointment updatedAppointment = new LibraryAppointment();
        updatedAppointment.setUserId(2L);
        updatedAppointment.setAppointmentType("Computer");
        updatedAppointment.setTimeSlot("11:00 AM - 12:00 PM");

        when(libraryAppointmentRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.updateAppointment(nonExistentId, updatedAppointment));
    }

    // Test for deleting a non-existent appointment
    @Test
    public void testDeleteNonExistentAppointment() {
        Long nonExistentId = 999L;
        doThrow(new EmptyResultDataAccessException(1)).when(libraryAppointmentRepository).deleteById(nonExistentId);
        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.deleteAppointment(nonExistentId));
    }
    //...
}
