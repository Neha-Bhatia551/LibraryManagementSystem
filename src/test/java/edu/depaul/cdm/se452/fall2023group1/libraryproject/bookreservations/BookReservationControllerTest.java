package edu.depaul.cdm.se452.fall2023group1.libraryproject.bookreservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservation;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservationRepository;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.ReservationType;
import org.apache.tomcat.util.digester.SystemPropertySource;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;


@SpringBootTest
@AutoConfigureMockMvc
public class BookReservationControllerTest {
    //TODO: add unit test cases
    private static final String RESERVATION_URL = "/api/bookreservations";

    @Autowired
    private BookReservationRepository reservationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllReservations() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RESERVATION_URL));
        var recordCount = (int) reservationRepository.count();
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

    @Test
    public void addReservation() throws Exception {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        BookReservation reservation = BookReservation.builder().bookId(123).reservationId(1).
                userId(12).borrowDate(currentTimestamp).returnDate(currentTimestamp).type(ReservationType.DIGITAL).build();
        String reservationAsJson = objectMapper.writeValueAsString(reservation);
        long beforeSize = reservationRepository.count();
        var request = MockMvcRequestBuilders.post(RESERVATION_URL)
                .contentType(MediaType.APPLICATION_JSON).contentType(reservationAsJson);
        ResultActions response = mockMvc.perform(request);
        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        long updatedReservationId = new ObjectMapper().readValue(jsonResponse, Long.class);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertNotEquals(updatedReservationId, reservation.getReservationId());
    }

    //TODO: fix this test case, problem is while passing timestamp values
//    @Test
//    public void addReservation() throws Exception {
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//
//        BookReservation reservation = BookReservation.builder().bookId(123).reservationId(1).
//                userId(12).borrowDate(currentTimestamp).returnDate(currentTimestamp).type(ReservationType.DIGITAL).build();
//        String reservationAsJson = objectMapper.writeValueAsString(reservation);
//        long beforeSize = reservationRepository.count();
//        var request = MockMvcRequestBuilders.post(RESERVATION_URL)
//                .contentType(MediaType.APPLICATION_JSON).contentType(reservationAsJson);
//        ResultActions response = mockMvc.perform(request);
//        var jsonResponse = response.andReturn().getResponse().getContentAsString();
//        //response.andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    public void removeStudent() throws Exception {
        long beforeSize = reservationRepository.count();
        var request = MockMvcRequestBuilders.delete(RESERVATION_URL+"/1");
        ResultActions response = mockMvc.perform(request);
        long afterSize = reservationRepository.count();
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }


}
