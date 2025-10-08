package ScoreSense.App.controller;

import ScoreSense.App.model.League;
import ScoreSense.App.repository.LeagueRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueRepository leagueRepository;
    public LeagueController(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @GetMapping public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @GetMapping("/{id}")
    public League getLeagueById(@PathVariable Long id) {
        return leagueRepository.findById(id).orElse(null);
    }

    @PostMapping public League createLeague(@RequestBody League league) {
        return leagueRepository.save(league);
    }

    @PutMapping("/{id}")
    public League updateLeague(@PathVariable Long id, @RequestBody League leagueDetails) {
        return leagueRepository.findById(id).map(league -> {
            league.setName(leagueDetails.getName());
            league.setCountry(leagueDetails.getCountry());
            league.setSeason(leagueDetails.getSeason());
            league.setLevel(leagueDetails.getLevel());
            league.setTeams(leagueDetails.getTeams());
            return leagueRepository.save(league);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteLeague(@PathVariable Long id) {
        leagueRepository.deleteById(id);
    }

}
