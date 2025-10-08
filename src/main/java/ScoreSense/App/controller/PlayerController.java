package ScoreSense.App.controller;

import ScoreSense.App.model.Player;
import ScoreSense.App.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/players")

public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/{id}")
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
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }
}
