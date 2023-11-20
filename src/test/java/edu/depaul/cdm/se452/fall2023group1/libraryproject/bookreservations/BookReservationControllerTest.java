package edu.depaul.cdm.se452.fall2023group1.libraryproject.bookreservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservation;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.BookReservationRepository;
import edu.depaul.cdm.se452.fall2023group1.bookreservations.ReservationType;
import edu.depaul.cdm.se452.fall2023group1.books.Book;
import edu.depaul.cdm.se452.fall2023group1.books.BookService;
import edu.depaul.cdm.se452.fall2023group1.user.User;
import edu.depaul.cdm.se452.fall2023group1.user.UserRepository;
import jakarta.transaction.Transactional;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookReservationControllerTest {
    //TODO: add unit test cases
    private static final String RESERVATION_URL = "/api/bookreservations";

    @Autowired
    private BookReservationRepository reservationRepository;

    @Autowired
    private BookService service;

    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public void addReservation() throws Exception {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        long beforeSize = reservationRepository.count();
        Optional<Book> book = service.findById(1L);
        Optional<User> user = userRepository.findById(1L);
        BookReservation reservation = BookReservation.builder().book(book.get()).
                user(user.get()).borrowDate(currentTimestamp).returnDate(currentTimestamp).type(ReservationType.DIGITAL).build();
        String reservationAsJson = objectMapper.writeValueAsString(reservation);
        var request = MockMvcRequestBuilders.post(RESERVATION_URL)
                .contentType(MediaType.APPLICATION_JSON).content(reservationAsJson);
        ResultActions response = mockMvc.perform(request);
        long afterSize = reservationRepository.count();
        var str = response.andReturn().getResponse().getContentAsString();
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(str,"New reservation id is 3");
        assertEquals(beforeSize + 1, afterSize);

    }

    @Test
    public void removeStudent() throws Exception {
        long beforeSize = reservationRepository.count();
        var request = MockMvcRequestBuilders.delete(RESERVATION_URL+"/delete/id/1");
        ResultActions response = mockMvc.perform(request);
        long afterSize = reservationRepository.count();
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void getReservationById() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RESERVATION_URL + "/id/1"));
        var str = response.andReturn().getResponse().getContentAsString();
        BookReservation reservation = new ObjectMapper().readValue(str, BookReservation.class);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(reservation.getReservationId(), 1);
    }

    @Test
    public void getReservationByUserId() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RESERVATION_URL + "/userid/1"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
    }

    @Test
    public void getReservationByBookId() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(RESERVATION_URL + "/bookid/1"));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
    }


}
