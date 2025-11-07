package scoresense.app.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import scoresense.app.dto.RefereeRequest;
import scoresense.app.dto.RefereeResponse;
import scoresense.app.service.RefereeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean; 
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when; 

@SpringBootTest
@AutoConfigureMockMvc

public class RefereeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean 
    private RefereeService refereeService;

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

    @Test
    void testFindByNationality_Success() throws Exception{
        String nationality= "Mexico";

        RefereeResponse mockReferee= RefereeResponse.builder()
        .refereeId(1L)
        .name("Kosean Mendez")
        .nationality(nationality)
        .experienceYears(10)
        .build();

       when(refereeService.findByNationality(eq(nationality)))
                .thenReturn(List.of(mockReferee));

        mockMvc.perform(get("/api/referees/search/nationality")
                .param("nationality", nationality)) 
                
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$").isArray()) 
                .andExpect(jsonPath("$[0].name").value("kosean Mendez"))
                .andExpect(jsonPath("$[0].nationality").value(nationality));
    }
    @Test
    void testFindByExperienceRange_Success() throws Exception {
        int min = 5;
        int max = 15;
        
        RefereeResponse mockReferee = RefereeResponse.builder()
                .refereeId(2L)
                .name("Kosean Mendez")
                .nationality("Argentina")
                .experienceYears(12) 
                .build();

  
        when(refereeService.findByExperienceRange(anyInt(), anyInt())) 
                .thenReturn(List.of(mockReferee));

        mockMvc.perform(get("/api/referees/search/experience")
                .param("minYears", String.valueOf(min)) 
                .param("maxYears", String.valueOf(max))) 
                
               
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$").isArray()) 
                .andExpect(jsonPath("$[0].name").value("Kosean Mendez"));
    }
    
}
