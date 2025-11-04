package scoresense.app.controller;

import scoresense.app.model.Player;
import scoresense.app.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
@RestController
@RequestMapping("/api/players")

public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    @Operation(summary = "Get players", description = "Get all players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a player", description = "Get a player by ID")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a player", description = "Create a player by ID")
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a player", description = "Update a player by ID")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        return playerRepository.findById(id).map(player -> {
            player.setName(playerDetails.getName());
            player.setPosition(playerDetails.getPosition());
            player.setAge(playerDetails.getAge());
            player.setNationality(playerDetails.getNationality());
            player.setHeight(playerDetails.getHeight());
            player.setWeight(playerDetails.getWeight());
            player.setTeam(playerDetails.getTeam());
            return playerRepository.save(player); }).orElse(null);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player", description = "Delete a player by ID")
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }
}
