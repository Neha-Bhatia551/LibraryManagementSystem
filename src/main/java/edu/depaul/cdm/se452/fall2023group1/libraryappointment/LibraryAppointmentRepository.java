package edu.depaul.cdm.se452.fall2023group1.libraryappointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibraryAppointmentRepository extends JpaRepository<LibraryAppointment, Long> {
    List<LibraryAppointment> findByAppointmentTypeAndTimeSlot(String appointmentType, String timeSlot);
}



