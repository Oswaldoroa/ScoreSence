package ScoreSense.App.controller;

import ScoreSense.App.model.Team;
import ScoreSense.App.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() { return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .map(ResponseEntity::ok) .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(teamDetails.getName());
                    team.setCountry(teamDetails.getCountry());
                    team.setFounded_year(teamDetails.getFounded_year());
                    team.setStadium(teamDetails.getStadium());
                    team.setLogo_url(teamDetails.getLogo_url());
                    team.setLeague(teamDetails.getLeague());
                    return ResponseEntity.ok(teamRepository.save(team)); })
                .orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
//        return teamRepository.findById(id)
//                .map(team -> {
//                    teamRepository.delete(team);
//                    return ResponseEntity.noContent().build(); })
//                .orElse(ResponseEntity.notFound().build());
//    }
}
