package edu.depaul.cdm.se452.fall2023group1.libraryproject.libraryappointment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointment;
import edu.depaul.cdm.se452.fall2023group1.libraryappointment.LibraryAppointmentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryAppointmentRepositoryIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(LibraryAppointmentRepositoryIntegrationTest.class);

    @Autowired
    private LibraryAppointmentRepository libraryAppointmentRepository;

    @Test
    @Order(1)
    public void testSaveAppointment() {
        LibraryAppointment appointment = new LibraryAppointment();
        appointment.setUserId(1L);
        appointment.setAppointmentType("General");
        appointment.setTimeSlot("10:00 AM - 11:00 AM");

        LibraryAppointment savedAppointment = libraryAppointmentRepository.save(appointment);
        assertNotNull(savedAppointment.getId());
        logger.info("Saved appointment with ID: {}", savedAppointment.getId());
    }

    @Test
    @Order(2)
    public void testFindAppointmentById() {
        // Assuming an appointment with ID 1 exists
        Optional<LibraryAppointment> foundAppointment = libraryAppointmentRepository.findById(1L);
        assertTrue(foundAppointment.isPresent());
        assertEquals(1L, foundAppointment.get().getId());
    }

    @Test
    @Order(3)
    public void testUpdateAppointment() {
        Long appointmentId = 1L; // Assuming this ID exists
        Optional<LibraryAppointment> optionalAppointment = libraryAppointmentRepository.findById(appointmentId);
        assertTrue(optionalAppointment.isPresent());

        LibraryAppointment appointment = optionalAppointment.get();
        appointment.setAppointmentType("Updated Type");
        LibraryAppointment updatedAppointment = libraryAppointmentRepository.save(appointment);

        assertEquals("Updated Type", updatedAppointment.getAppointmentType());
    }

    @Test
    @Order(4)
    public void testDeleteAppointment() {
        Long appointmentId = 1L; // Assuming this ID exists
        libraryAppointmentRepository.deleteById(appointmentId);
        Optional<LibraryAppointment> deletedAppointment = libraryAppointmentRepository.findById(appointmentId);
        assertFalse(deletedAppointment.isPresent());
    }

    @Test
    @Order(5)
    public void testFindNonExistentAppointment() {
        Long nonExistentId = 999L;
        Optional<LibraryAppointment> nonExistentAppointment = libraryAppointmentRepository.findById(nonExistentId);
        assertFalse(nonExistentAppointment.isPresent());
    }
}
