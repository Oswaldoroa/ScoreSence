package scoresense.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.Poll_voteRequest;
import scoresense.app.dto.Poll_voteResponse;
import scoresense.app.service.Poll_voteService;

@RestController
@RequestMapping("/api/poll-votes")
@Tag(name = "Poll Votes", description = "Poll Votes API Endpoints")
public class Poll_voteController {

    private final Poll_voteService poll_voteService;

    public Poll_voteController(Poll_voteService poll_voteService) {
        this.poll_voteService = poll_voteService;
    }

    @PostMapping
    @Operation(summary = "Create a new vote", description = "Register a new vote for a poll")
    public ResponseEntity<Poll_voteResponse> create(@Valid @RequestBody Poll_voteRequest req) {
        Poll_voteResponse created = poll_voteService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/by-poll/{pollId}")
    @Operation(summary = "Get all votes by poll ID", description = "Retrieve all votes associated with a poll")
    public ResponseEntity<List<Poll_voteResponse>> getVotesByPoll(@PathVariable Long pollId) {
        return ResponseEntity.ok(poll_voteService.getVotesByPoll(pollId));
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "Get all votes by user ID", description = "Retrieve all votes made by a specific user")
    public ResponseEntity<List<Poll_voteResponse>> getVotesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(poll_voteService.getVotesByUser(userId));
    }
}
