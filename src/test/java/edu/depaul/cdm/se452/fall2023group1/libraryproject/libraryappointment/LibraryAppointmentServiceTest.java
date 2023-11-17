package edu.depaul.cdm.se452.fall2023group1.libraryproject.libraryappointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import java.util.List;
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
        appointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

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
        appointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(appointment);
        LibraryAppointment savedAppointment = libraryAppointmentService.reserveComputer(appointment);

        assertEquals("Computer", savedAppointment.getAppointmentType());
    }

    @Test
    public void testReserveBook() {
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(1L);
        appointment.setAppointmentType("Book");
        appointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

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
        existingAppointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

        LibraryAppointment updatedAppointment = new LibraryAppointment();
        updatedAppointment.setUserId(2L);
        updatedAppointment.setAppointmentType("Computer");
        updatedAppointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

        when(libraryAppointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(updatedAppointment);

        LibraryAppointment result = libraryAppointmentService.updateAppointment(appointmentId, updatedAppointment);

        assertEquals("Computer", result.getAppointmentType());
        assertEquals("01/02/2023 11:00 - 01/02/2023 12:00", result.getTimeSlot());
    }

    @Test
    public void testMakeReservationWithMissingFields() {
        LibraryAppointment appointment = new LibraryAppointment();
        // Missing appointmentType
        appointment.setUserId(1L);
        appointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.makeReservation(appointment));
    }

    @Test
    public void testUpdateNonExistentAppointment() {
        Long nonExistentId = 999L;
        LibraryAppointment updatedAppointment = new LibraryAppointment();
        updatedAppointment.setUserId(2L);
        updatedAppointment.setAppointmentType("Computer");
        updatedAppointment.setTimeSlot("01/02/2023 11:00 - 01/02/2023 12:00");

        when(libraryAppointmentRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.updateAppointment(nonExistentId, updatedAppointment));
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        Long nonExistentId = 999L;
        doThrow(new EmptyResultDataAccessException(1)).when(libraryAppointmentRepository).deleteById(nonExistentId);
        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.deleteAppointment(nonExistentId));
    }

    // test for updating an appointment successfully
    @Test
    public void testSuccessfulUpdateAppointment() {
        // Create an appointment
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(123L);
        appointment.setAppointmentType("General");
        appointment.setTimeSlot("01/01/2023 10:00 - 01/01/2023 11:00");

        // Mock the save method to return an appointment with an ID set
        LibraryAppointment savedAppointment = new LibraryAppointment();
        savedAppointment.setId(1L); // Set an ID
        savedAppointment.setUserId(appointment.getUserId());
        savedAppointment.setAppointmentType(appointment.getAppointmentType());
        savedAppointment.setTimeSlot(appointment.getTimeSlot());
        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(savedAppointment);

        // Mock findById to return the saved appointment
        when(libraryAppointmentRepository.findById(1L)).thenReturn(Optional.of(savedAppointment));

        // Update the appointment
        savedAppointment.setAppointmentType("Updated Type");
        when(libraryAppointmentRepository.save(any(LibraryAppointment.class))).thenReturn(savedAppointment);
        LibraryAppointment updatedAppointment = libraryAppointmentService.updateAppointment(savedAppointment.getId(), savedAppointment);

        // Assert the changes
        assertEquals("Updated Type", updatedAppointment.getAppointmentType());
    }

    // test for deleting an appointment successfully
    @Test
    public void testSuccessfulDeleteAppointment() {
        // Create an appointment and assume it is saved with an ID
        Long appointmentId = 1L;
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setId(appointmentId);
        appointment.setUserId(123L);
        appointment.setAppointmentType("General");
        appointment.setTimeSlot("01/01/2023 10:00 - 01/01/2023 11:00");

        // Mock findById to return the appointment before deletion
        when(libraryAppointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        // Perform the delete operation
        libraryAppointmentService.deleteAppointment(appointmentId);

        // After deletion, mock findById to simulate that the appointment no longer exists
        when(libraryAppointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        // Verify that the deleteById method was called
        verify(libraryAppointmentRepository, times(1)).deleteById(appointmentId);

        // Verify that the appointment no longer exists
        assertThrows(LibraryAppointmentException.class, () -> libraryAppointmentService.getAppointment(appointmentId));
    }

    @Test
    public void testOverlappingAppointments() {
        // Create a mock appointment that already exists
        LibraryAppointment existingAppointment = new LibraryAppointment();
        existingAppointment.setUserId(123L);
        existingAppointment.setAppointmentType("Computer");
        existingAppointment.setTimeSlot("01/09/2023 11:00 - 01/09/2023 12:00");

        // Mock the repository response
        when(libraryAppointmentRepository.findByAppointmentTypeAndTimeSlot(
                existingAppointment.getAppointmentType(), existingAppointment.getTimeSlot()))
                .thenReturn(List.of(existingAppointment));

        // Create a new appointment that overlaps with the existing one
        LibraryAppointment newAppointment = new LibraryAppointment();
        newAppointment.setUserId(456L);
        newAppointment.setAppointmentType("Computer");
        newAppointment.setTimeSlot("01/09/2023 11:00 - 01/09/2023 12:00");

        // Test for overlap
        boolean isOverlapping = libraryAppointmentService.isOverlappingAppointment(newAppointment);
        assertTrue(isOverlapping, "The new appointment should overlap with the existing one.");
    }


}
