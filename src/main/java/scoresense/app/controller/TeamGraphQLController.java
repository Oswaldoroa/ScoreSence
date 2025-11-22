package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.service.TeamService;

import java.util.List;

@Controller
public class TeamGraphQLController {

    private final TeamService teamService;

    public TeamGraphQLController(TeamService teamService) {
        this.teamService = teamService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<TeamResponse> teams() {
        return teamService.getAll();
    }

    @QueryMapping
    public TeamResponse teamById(@Argument Long id) {
        return teamService.getById(id);
    }

    @QueryMapping
    public List<TeamResponse> searchTeamsByName(@Argument String name) {
        return teamService.findByName(name);
    }

    @QueryMapping
    public List<TeamResponse> searchTeamsByLeagueName(@Argument String leagueName) {
        return teamService.findByLeagueName(leagueName);
    }

    @QueryMapping
    public List<TeamResponse> searchTeamsByCountry(@Argument String country) {
        return teamService.findByCountry(country);
    }

    @QueryMapping
    public List<TeamResponse> pagedTeams(@Argument int page, @Argument int size) {
        return teamService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public TeamResponse createTeam(
            @Argument String name,
            @Argument String country,
            @Argument Short foundedYear,
            @Argument String stadium,
            @Argument String logoUrl,
            @Argument Long leagueId
    ) {
        TeamRequest req = new TeamRequest();
        req.setName(name);
        req.setCountry(country);
        req.setFoundedYear(foundedYear);
        req.setStadium(stadium);
        req.setLogoUrl(logoUrl);
        req.setLeagueId(leagueId);
        return teamService.create(req);
    }

    @MutationMapping
    public TeamResponse updateTeam(
            @Argument Long teamId,
            @Argument String name,
            @Argument String country,
            @Argument Short foundedYear,
            @Argument String stadium,
            @Argument String logoUrl,
            @Argument Long leagueId
    ) {
        TeamRequest req = new TeamRequest();
        req.setName(name);
        req.setCountry(country);
        req.setFoundedYear(foundedYear);
        req.setStadium(stadium);
        req.setLogoUrl(logoUrl);
        req.setLeagueId(leagueId);
        return teamService.update(teamId, req);
    }
}