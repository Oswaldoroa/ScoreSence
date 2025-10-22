package ScoreSense.App.Controller;

import ScoreSense.App.dto.CoachRequest;
import ScoreSense.App.model.League;
import ScoreSense.App.model.Team;
import ScoreSense.App.repository.LeagueRepository;
import ScoreSense.App.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CoachControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCoach() throws Exception {
        League league = new League();
        league.setName("Liga MX");
        league.setCountry("México");
        league.setSeason("2025");
        league.setLevel("Primera");
        leagueRepository.save(league);

        Team team = new Team();
        team.setName("Club Aztecas");
        team.setCountry("México");
        team.setFoundedYear((short) 1995);
        team.setStadium("Estadio Azteca");
        team.setLogoUrl("https://example.com/logo.png");
        team.setLeague(league);
        teamRepository.save(team);

        CoachRequest request = new CoachRequest();
        request.setName("Juan Pérez");
        request.setNationality("Mexicana");
        request.setExperiencedYears(10);
        request.setTeamId(team.getTeamId());

        mockMvc.perform(post("/api/coaches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}