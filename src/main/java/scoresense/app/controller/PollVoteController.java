package scoresense.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.PollVoteRequest;
import scoresense.app.dto.PollVoteResponse;
import scoresense.app.service.PollVoteService;

@RestController
@RequestMapping("/api/poll-votes")
@Tag(name = "PollVotes", description = "PollVote API Endpoints")
public class PollVoteController {

    private final PollVoteService pollVoteService;

    public PollVoteController(PollVoteService pollVoteService) {
        this.pollVoteService = pollVoteService;
    }

    @GetMapping
    @Operation(summary = "List all poll votes")
    public ResponseEntity<List<PollVoteResponse>> getAll() {
        return ResponseEntity.ok(pollVoteService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get poll vote by ID")
    public ResponseEntity<PollVoteResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pollVoteService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new poll vote")
    public ResponseEntity<PollVoteResponse> create(@Valid @RequestBody PollVoteRequest req) {
        PollVoteResponse created = pollVoteService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update poll vote")
    public ResponseEntity<PollVoteResponse> update(@PathVariable Long id, @Valid @RequestBody PollVoteRequest req) {
        PollVoteResponse updated = pollVoteService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete poll vote")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pollVoteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    @GetMapping("/paged")
    @Operation(summary = "Page poll votes")
    public ResponseEntity<Page<PollVoteResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(pollVoteService.getAllPaged(pageable));
    }

    @GetMapping("/by-user")
    @Operation(summary = "Search poll votes by user")
    public ResponseEntity<List<PollVoteResponse>> getByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(pollVoteService.findByUser(userId));
    }

    @GetMapping("/by-option")
    @Operation(summary = "Search poll votes by option selected")
    public ResponseEntity<List<PollVoteResponse>> getByOption(@RequestParam String option) {
        return ResponseEntity.ok(pollVoteService.findByOptionSelected(option));
    }

    @GetMapping("/voted-within")
    @Operation(summary = "Search poll votes within X days")
    public ResponseEntity<List<PollVoteResponse>> getByVotedWithin(@RequestParam int days) {
        return ResponseEntity.ok(pollVoteService.findByVotedWithin(days));
    }
}