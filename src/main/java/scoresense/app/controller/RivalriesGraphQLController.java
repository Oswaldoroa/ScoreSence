package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;
import scoresense.app.dto.RivalriesRequest;
import scoresense.app.dto.RivalriesResponse;
import scoresense.app.service.RivalriesService;

import java.util.List;

@Controller
public class RivalriesGraphQLController {

    private final RivalriesService service;

    public RivalriesGraphQLController(RivalriesService service) {
        this.service = service;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<RivalriesResponse> rivalries() {
        return service.getAll();
    }

    @QueryMapping
    public RivalriesResponse rivalryById(@Argument Long id) {
        return service.getById(id);
    }

    @QueryMapping
    public List<RivalriesResponse> pagedRivalries(@Argument int page, @Argument int size) {
        return service.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public RivalriesResponse createRivalry(
            @Argument String description,
            @Argument Long teamVisitorId,
            @Argument Long teamLocalId
    ) {
        RivalriesRequest req = RivalriesRequest.builder()
                .description(description)
                .teamVisitorId(teamVisitorId)
                .teamLocalId(teamLocalId)
                .build();
        return service.create(req);
    }

    @MutationMapping
    public RivalriesResponse updateRivalry(
            @Argument Long rivalrieId,
            @Argument String description,
            @Argument Long teamVisitorId,
            @Argument Long teamLocalId
    ) {
        RivalriesRequest req = RivalriesRequest.builder()
                .description(description)
                .teamVisitorId(teamVisitorId)
                .teamLocalId(teamLocalId)
                .build();
        return service.update(rivalrieId, req);
    }

    @MutationMapping
    public String deleteRivalry(@Argument Long rivalrieId) {
        service.delete(rivalrieId);
        return "Rivalry deleted successfully";
    }

    @QueryMapping
    public List<RivalriesResponse> searchRivalrieVisitor(@Argument String visitorName) {
        return service.searchByVisitorName(visitorName);
    }

    @QueryMapping
    public List<RivalriesResponse> searchRivalrieLocal(@Argument String localName) {
        return service.searchByLocalName(localName);
    }

    @QueryMapping
    public List<RivalriesResponse> searchRivalrieByDescription(@Argument String description) {
        return service.searchByDescription(description);
    }

}