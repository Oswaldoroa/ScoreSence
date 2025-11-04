package ScoreSense.App.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import ScoreSense.App.dto.RefereeRequest;

@SpringBootTest
@AutoConfigureMockMvc

public class RefereeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateReferee_Success() throws Exception {

        RefereeRequest request = new RefereeRequest();
        request.setName("Kosean Mendez");
        request.setNationality("Mexicana");
        request.setExperienceYears(15);

        String refereeJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/referees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(refereeJson))

                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateReferee_BadRequest_MissingName() throws Exception {

        RefereeRequest request = new RefereeRequest();
        request.setName(null);
        request.setNationality("México");
        request.setExperienceYears(8);

        String refereeJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/referees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(refereeJson))

                .andExpect(status().isBadRequest());
    }
}
