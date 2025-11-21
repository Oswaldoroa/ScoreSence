package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.LeagueRequest;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.service.LeagueService;

import java.util.List;

@Controller
public class LeagueGraphQLController {

    private final LeagueService leagueService;

    public LeagueGraphQLController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<LeagueResponse> leagues() {
        return leagueService.getAll();
    }

    @QueryMapping
    public LeagueResponse leagueById(@Argument Long id) {
        return leagueService.getById(id);
    }

    @QueryMapping
    public List<LeagueResponse> pagedLeagues(@Argument int page, @Argument int size) {
        return leagueService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public LeagueResponse createLeague(
            @Argument String name,
            @Argument String country,
            @Argument String season,
            @Argument String level
    ) {
        LeagueRequest req = new LeagueRequest();
        req.setName(name);
        req.setCountry(country);
        req.setSeason(season);
        req.setLevel(level);
        return leagueService.create(req);
    }

    @MutationMapping
    public LeagueResponse updateLeague(
            @Argument Long leagueId,
            @Argument String name,
            @Argument String country,
            @Argument String season,
            @Argument String level
    ) {
        LeagueRequest req = new LeagueRequest();
        req.setName(name);
        req.setCountry(country);
        req.setSeason(season);
        req.setLevel(level);
        return leagueService.update(leagueId, req);
    }
}