package scoresense.app.controller;

import org.springframework.web.bind.annotation.*;

import scoresense.app.model.PlayerStats;
import scoresense.app.repository.PlayerStatRepository;

import java.util.List;

@RestController
@RequestMapping("/api/player-stats")

public class PlayerStatsController {

    private final PlayerStatRepository playerStatRepository;
    public PlayerStatsController(PlayerStatRepository playerStatsRepository) {
        this.playerStatRepository = playerStatsRepository;
    }

    @GetMapping
    public List<PlayerStats> getAllPlayerStats() {
        return playerStatRepository.findAll();
    }
    @GetMapping("/{id}")
    public PlayerStats getPlayerStatById(@PathVariable Long id) {
        return playerStatRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PlayerStats createPlayerStat(@RequestBody PlayerStats playerStats) {
        return playerStatRepository.save(playerStats);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}") public void deletePlayerStat(@PathVariable Long id) {
        playerStatRepository.deleteById(id);
    }

}
