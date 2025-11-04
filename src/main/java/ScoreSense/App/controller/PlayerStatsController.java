package scoresense.app.controller;

import scoresense.app.model.PlayerStats;
import scoresense.app.repository.PlayerStatRepository;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/player-stats")

public class PlayerStatsController {

    private final PlayerStatRepository playerStatRepository;
    public PlayerStatsController(PlayerStatRepository playerStatsRepository) {
        this.playerStatRepository = playerStatsRepository;
    }

    @GetMapping
    @Operation(summary = "Get players stats", description = "Get all players stats")
    public List<PlayerStats> getAllPlayerStats() {
        return playerStatRepository.findAll();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a player stat", description = "Get a player stat by ID")
    public PlayerStats getPlayerStatById(@PathVariable Long id) {
        return playerStatRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a player stats", description = "Create a player stats by ID")
    public PlayerStats createPlayerStat(@RequestBody PlayerStats playerStats) {
        return playerStatRepository.save(playerStats);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a player stat", description = "Update a player stat by ID")
    public PlayerStats updatePlayerStat(@PathVariable Long id, @RequestBody PlayerStats updatedStats) {
        return playerStatRepository.findById(id).map(stats -> {
            stats.setGoals(updatedStats.getGoals());
            stats.setAssists(updatedStats.getAssists());
            stats.setYellowCards(updatedStats.getYellowCards());
            stats.setRedCards(updatedStats.getRedCards());
            stats.setMinutesPlayed(updatedStats.getMinutesPlayed());
            stats.setPlayer(updatedStats.getPlayer());
            stats.setMatch(updatedStats.getMatch());
            return playerStatRepository.save(stats); }).orElse(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player stat", description = "Delete a player stat by ID")
    public void deletePlaerStat(@PathVariable Long id) {
        playerStatRepository.deleteById(id);
    }

}
