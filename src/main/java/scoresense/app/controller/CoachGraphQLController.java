package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.CoachRequest;
import scoresense.app.dto.CoachResponse;
import scoresense.app.service.CoachService;

import java.util.List;

@Controller
public class CoachGraphQLController {

    private final CoachService coachService;

    public CoachGraphQLController(CoachService coachService) {
        this.coachService = coachService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<CoachResponse> coaches() {
        return coachService.getAll();
    }

    @QueryMapping
    public CoachResponse coachById(@Argument Long id) {
        return coachService.getById(id);
    }

    @QueryMapping
    public List<CoachResponse> searchCoachesByName(@Argument String name) {
        return coachService.findByName(name);
    }

    @QueryMapping
    public List<CoachResponse> searchCoachesByExperience(@Argument int years) {
        return coachService.findExperiencedCoaches(years);
    }

    @QueryMapping
    public List<CoachResponse> coachesByNationality(@Argument String nationality) {
        return coachService.findByNationality(nationality);
    }

    @QueryMapping
    public List<CoachResponse> pagedCoaches(@Argument int page, @Argument int size) {
        return coachService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public CoachResponse createCoach(
            @Argument String name,
            @Argument String nationality,
            @Argument Integer experiencedYears,
            @Argument Long teamId
    ) {
        CoachRequest req = new CoachRequest();
        req.setName(name);
        req.setNationality(nationality);
        req.setExperiencedYears(experiencedYears);
        req.setTeamId(teamId);
        return coachService.create(req);
    }

    @MutationMapping
    public CoachResponse updateCoach(
            @Argument Long coachId,
            @Argument String name,
            @Argument String nationality,
            @Argument Integer experiencedYears,
            @Argument Long teamId
    ) {
        CoachRequest req = new CoachRequest();
        req.setName(name);
        req.setNationality(nationality);
        req.setExperiencedYears(experiencedYears);
        req.setTeamId(teamId);
        return coachService.update(coachId, req);
    }

    @MutationMapping
    public String deleteCoach(@Argument Long coachId) {
        coachService.delete(coachId);
        return "Coach deleted successfully";
    }
}