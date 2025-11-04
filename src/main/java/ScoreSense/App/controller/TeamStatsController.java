package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get teams stats", description = "Get all team stats")
    public List<TeamStats> getAllTeamStats() {
        return teamStatRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a team stat", description = "Get a team stat by ID")
    public Optional<TeamStats> getTeamStatById(@PathVariable Long id) {
        return teamStatRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a team stat", description = "Create a team stat by ID")
    public TeamStats createTeamStat(@RequestBody TeamStats teamStat) {
        return teamStatRepository.save(teamStat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a team stat", description = "Update a team stat by ID")
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
    @Operation(summary = "Delete a team stat", description = "Delete a team stat by ID")
    public void deleteTeamStat(@PathVariable Long id) {
        teamStatRepository.deleteById(id);
    }
}
