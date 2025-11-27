package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import scoresense.app.dto.PlayerRequest;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.service.PlayerService;

import java.util.List;

@Controller
public class PlayerGraphQLController {

    private final PlayerService playerService;

    public PlayerGraphQLController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<PlayerResponse> players() {
        return playerService.getAll();
    }

    @QueryMapping
    public PlayerResponse playerById(@Argument Long id) {
        return playerService.getById(id);
    }

    // --- MUTATIONS ---
    @MutationMapping
    public PlayerResponse createPlayer(
            @Argument String name,
            @Argument String position,
            @Argument Integer age,
            @Argument String nationality,
            @Argument Integer height,
            @Argument Integer weight,
            @Argument Long teamId
    ) {
        PlayerRequest req = new PlayerRequest();
        req.setName(name);
        req.setPosition(position);
        req.setAge(age);
        req.setNationality(nationality);
        req.setHeight(height);
        req.setWeight(weight);
        req.setTeamId(teamId);
        return playerService.create(req);
    }

    @MutationMapping
    public PlayerResponse updatePlayer(
            @Argument Long playerId,
            @Argument String name,
            @Argument String position,
            @Argument Integer age,
            @Argument String nationality,
            @Argument Integer height,
            @Argument Integer weight,
            @Argument Long teamId
    ) {
        PlayerRequest req = new PlayerRequest();
        req.setName(name);
        req.setPosition(position);
        req.setAge(age);
        req.setNationality(nationality);
        req.setHeight(height);
        req.setWeight(weight);
        req.setTeamId(teamId);
        return playerService.update(playerId, req);
    }

    @MutationMapping
    public String deletePlayer(@Argument Long playerId) {
        playerService.delete(playerId);
        return "Player deleted successfully";
    }
}