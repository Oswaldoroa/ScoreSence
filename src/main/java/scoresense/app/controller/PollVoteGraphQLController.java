package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.PollVoteRequest;
import scoresense.app.dto.PollVoteResponse;
import scoresense.app.service.PollVoteService;

import java.util.List;

@Controller
public class PollVoteGraphQLController {

    private final PollVoteService pollVoteService;

    public PollVoteGraphQLController(PollVoteService pollVoteService) {
        this.pollVoteService = pollVoteService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<PollVoteResponse> pollVotes() {
        return pollVoteService.getAll();
    }

    @QueryMapping
    public PollVoteResponse pollVoteById(@Argument Long id) {
        return pollVoteService.getById(id);
    }

    @QueryMapping
    public List<PollVoteResponse> pollVotesByUser(@Argument Long userId) {
        return pollVoteService.findByUser(userId);
    }

    @QueryMapping
    public List<PollVoteResponse> pollVotesByOption(@Argument String option) {
        return pollVoteService.findByOptionSelected(option);
    }

    @QueryMapping
    public List<PollVoteResponse> pollVotesByVotedWithin(@Argument int days) {
        return pollVoteService.findByVotedWithin(days);
    }

    @QueryMapping
    public List<PollVoteResponse> pagedPollVotes(@Argument int page, @Argument int size) {
        return pollVoteService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public PollVoteResponse createPollVote(
            @Argument Long pollId,
            @Argument Long userId,
            @Argument String optionSelected
    ) {
        PollVoteRequest req = new PollVoteRequest();
        req.setPollId(pollId);
        req.setUserId(userId);
        req.setOptionSelected(optionSelected);
        return pollVoteService.create(req);
    }

    @MutationMapping
    public PollVoteResponse updatePollVote(
            @Argument Long pollVoteId,
            @Argument Long pollId,
            @Argument Long userId,
            @Argument String optionSelected
    ) {
        PollVoteRequest req = new PollVoteRequest();
        req.setPollId(pollId);
        req.setUserId(userId);
        req.setOptionSelected(optionSelected);
        return pollVoteService.update(pollVoteId, req);
    }

    @MutationMapping
    public String deletePollVote(@Argument Long pollVoteId) {
        pollVoteService.delete(pollVoteId);
        return "Poll vote deleted successfully";
    }
}