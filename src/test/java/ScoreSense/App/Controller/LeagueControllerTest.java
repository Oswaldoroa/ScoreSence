package scoresense.app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import scoresense.app.dto.LeagueRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateLeague_Success() throws Exception {
        String expectedName = "Liga MX";

        LeagueRequest request = new LeagueRequest();
        request.setName(expectedName);
        request.setCountry("México");
        request.setSeason("2025");
        request.setLevel("Primera División");

        String leagueJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(leagueJson))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(expectedName)) // Verifica el nombre devuelto
                .andExpect(jsonPath("$.league_id").exists());      // Verifica que el ID fue generado
    }

    @Test
    public void testCreateLeague_BadRequest_MissingCountry() throws Exception {

        LeagueRequest request = new LeagueRequest();
        request.setName("La Liga");
        request.setCountry(null);
        request.setSeason("2024");
        request.setLevel("Primera");

        String leagueJson = objectMapper.writeValueAsString(request);

        // 2. Ejecutar la petición simulada POST
        mockMvc.perform(post("/api/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(leagueJson))

                .andExpect(status().isBadRequest());
    }
}