package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.service.PollService;

import java.util.List;

@Controller
public class PollGraphQLController {

    private final PollService pollService;

    public PollGraphQLController(PollService pollService) {
        this.pollService = pollService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<PollResponse> polls() {
        return pollService.getAll();
    }

    @QueryMapping
    public PollResponse pollById(@Argument Long id) {
        return pollService.getById(id);
    }

    @QueryMapping
    public List<PollResponse> pollsByQuestion(@Argument String question) {
        return pollService.findByQuestion(question);
    }

    @QueryMapping
    public List<PollResponse> pollsByCreatedWithin(@Argument int days) {
        return pollService.findByCreatedWithin(days);
    }

    @QueryMapping
    public List<PollResponse> pollsByExpiredWithin(@Argument int days) {
        return pollService.findByExpiredWithin(days);
    }

    @QueryMapping
    public List<PollResponse> pagedPolls(@Argument int page, @Argument int size) {
        return pollService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public PollResponse createPoll(
            @Argument String question,
            @Argument String expiresAt
    ) {
        PollRequest req = new PollRequest();
        req.setQuestion(question);
        req.setExpiresAt(java.time.LocalDateTime.parse(expiresAt));
        return pollService.create(req);
    }

    @MutationMapping
    public PollResponse updatePoll(
            @Argument Long pollId,
            @Argument String question,
            @Argument String expiresAt
    ) {
        PollRequest req = new PollRequest();
        req.setQuestion(question);
        req.setExpiresAt(java.time.LocalDateTime.parse(expiresAt));
        return pollService.update(pollId, req);
    }

    @MutationMapping
    public String deletePoll(@Argument Long pollId) {
        pollService.delete(pollId);
        return "Poll deleted successfully";
    }
}