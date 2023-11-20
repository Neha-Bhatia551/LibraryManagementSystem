package edu.depaul.cdm.se452.fall2023group1.libraryproject.reviews;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.depaul.cdm.se452.fall2023group1.reviews.Review;
import edu.depaul.cdm.se452.fall2023group1.reviews.ReviewRepository;
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReviewControllerTest {
    private static final String TEST_URL = "/api/reviews/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetReviewById() throws Exception {
        int id = 1;
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL + id));
        response.andExpect(MockMvcResultMatchers.status().isOk());
        var jsonResponse = response.andReturn().getResponse().getContentAsString();
        Review result = new ObjectMapper().readValue(jsonResponse, Review.class);
        assertEquals(id, result.getReviewId());
    }

    @Test
    public void testAddReview() throws Exception {
        Review r = new Review();
        r.setUserId(12345L);
        r.setBookId(1L);
        r.setIsbn("123-456-234");
        r.setStars(5);
        r.setDescription("review text");

        long count = reviewRepository.count();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(TEST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(r)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(r.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(r.getDescription()));

        long countNew = reviewRepository.count();
        assertEquals(count+1, countNew);
    }

    @Test
    public void testUpdateReview() throws Exception {
//         Use pre-set review with ID 2. Has rating = 4 and review = 'a short description for book 2'
        String newDescription = "new review";
        Integer newRating = 1;
        Review r = new Review();
        r.setUserId(12345L);
        r.setBookId(1L);
        r.setIsbn("");
        r.setStars(newRating);
        r.setDescription(newDescription);

        long count = reviewRepository.count();
        System.out.println(TEST_URL + "2");
        mockMvc.perform(MockMvcRequestBuilders
                        .put(TEST_URL + "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(r)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(newRating))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(newDescription));
        long countNew = reviewRepository.count();
        System.out.println(countNew);
        assertEquals(count, countNew);
    }

    @Test
    public void testDeleteReview() throws Exception {
//         Use pre-set review with ID 3.
        long count = reviewRepository.count();
        var request = MockMvcRequestBuilders.delete(TEST_URL+"/3");
        ResultActions response = mockMvc.perform(request);
        long countNew = reviewRepository.count();
        response.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(count-1, countNew);
    }

    @Test
    public void testGetReviewByBook() throws Exception {
//        http://localhost:8080/api/reviews/book?id=223
        int bookId = 1;
        mockMvc.perform(MockMvcRequestBuilders
                    .get(TEST_URL + "/book?id=" + bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].reviewId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].reviewId").value(3));
    }

    @Test
    public void testGetReviewByBookAndRating() throws Exception {
//        http://localhost:8080/api/reviews/book?id=223&stars=5
        int bookId = 1;
        int rating = 5;
        mockMvc.perform(MockMvcRequestBuilders
                        .get(TEST_URL + "/book?id=" + bookId + "&starts=" + rating))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bookId").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].stars").value(rating));
    }

    @Test
    public void testGetReviewByUser() throws Exception {
//        localhost:8080/api/reviews/author?user=123
    }

}
