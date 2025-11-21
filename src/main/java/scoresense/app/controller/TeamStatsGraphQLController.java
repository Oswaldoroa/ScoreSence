package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;
import scoresense.app.dto.TeamStatsRequest;
import scoresense.app.dto.TeamStatsResponse;
import scoresense.app.service.TeamStatsService;

import java.util.List;

@Controller
public class TeamStatsGraphQLController {

    private final TeamStatsService teamStatsService;

    public TeamStatsGraphQLController(TeamStatsService teamStatsService) {
        this.teamStatsService = teamStatsService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<TeamStatsResponse> teamStats() {
        return teamStatsService.getAll();
    }

    @QueryMapping
    public TeamStatsResponse teamStatById(@Argument Long id) {
        return teamStatsService.getById(id);
    }

    @QueryMapping
    public List<TeamStatsResponse> teamStatsByFouls(@Argument Integer fouls) {
        return teamStatsService.findByFouls(fouls);
    }

    @QueryMapping
    public List<TeamStatsResponse> teamStatsByShots(@Argument Integer shots) {
        return teamStatsService.findByShots(shots);
    }

    @QueryMapping
    public List<TeamStatsResponse> teamStatsByTeam(@Argument Long teamId) {
        return teamStatsService.findByTeamId(teamId);
    }

    @QueryMapping
    public List<TeamStatsResponse> pagedTeamStats(@Argument int page, @Argument int size) {
        return teamStatsService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public TeamStatsResponse createTeamStat(
            @Argument Integer possesion,
            @Argument Integer shots,
            @Argument Integer fouls,
            @Argument Integer corners,
            @Argument Long teamId,
            @Argument Long matchId
    ) {
        TeamStatsRequest req = new TeamStatsRequest();
        req.setPossesion(possesion);
        req.setShots(shots);
        req.setFouls(fouls);
        req.setCorners(corners);
        req.setTeamId(teamId);
        req.setMatchId(matchId);
        return teamStatsService.create(req);
    }

    @MutationMapping
    public TeamStatsResponse updateTeamStat(
            @Argument Long teamStatId,
            @Argument Integer possesion,
            @Argument Integer shots,
            @Argument Integer fouls,
            @Argument Integer corners,
            @Argument Long teamId,
            @Argument Long matchId
    ) {
        TeamStatsRequest req = new TeamStatsRequest();
        req.setPossesion(possesion);
        req.setShots(shots);
        req.setFouls(fouls);
        req.setCorners(corners);
        req.setTeamId(teamId);
        req.setMatchId(matchId);
        return teamStatsService.update(teamStatId, req);
    }

    @MutationMapping
    public String deleteTeamStat(@Argument Long teamStatId) {
        teamStatsService.delete(teamStatId);
        return "TeamStats deleted successfully";
    }
}