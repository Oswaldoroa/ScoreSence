package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import scoresense.app.model.TeamStats;
import scoresense.app.repository.TeamStatRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team-stats")

public class TeamStatsController {

    @Autowired
    private TeamStatRepository teamStatRepository;

    @GetMapping
    public List<TeamStats> getAllTeamStats() {
        return teamStatRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TeamStats> getTeamStatById(@PathVariable Long id) {
        return teamStatRepository.findById(id);
    }

    @PostMapping
    public TeamStats createTeamStat(@RequestBody TeamStats teamStat) {
        return teamStatRepository.save(teamStat);
    }

    @PutMapping("/{id}")
    public TeamStats updateTeamStat(@PathVariable Long id, @RequestBody TeamStats updatedTeamStat) {
        return teamStatRepository.findById(id)
                .map(teamStat -> {
                    teamStat.setPossesion(updatedTeamStat.getPossesion());
                    teamStat.setShots(updatedTeamStat.getShots());
                    teamStat.setFouls(updatedTeamStat.getFouls());
                    teamStat.setCorners(updatedTeamStat.getCorners());
                    teamStat.setTeam(updatedTeamStat.getTeam());
                    teamStat.setMatch(updatedTeamStat.getMatch());
                    return teamStatRepository.save(teamStat);
                })
                .orElseGet(() -> {
                    updatedTeamStat.setTeamStatId(id);
                    return teamStatRepository.save(updatedTeamStat);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteTeamStat(@PathVariable Long id) {
        teamStatRepository.deleteById(id);
    }
}
