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
import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.service.PollService;

@RestController
@RequestMapping("/api/polls")
@Tag(name = "Polls", description = "Poll API Endpoints")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    @Operation(summary = "List all polls")
    public ResponseEntity<List<PollResponse>> getAll() {
        return ResponseEntity.ok(pollService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get poll by ID")
    public ResponseEntity<PollResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pollService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new poll")
    public ResponseEntity<PollResponse> create(@Valid @RequestBody PollRequest req) {
        PollResponse created = pollService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update poll")
    public ResponseEntity<PollResponse> update(@PathVariable Long id, @Valid @RequestBody PollRequest req) {
        PollResponse updated = pollService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete poll")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pollService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    @GetMapping("/paged")
    @Operation(summary = "Page polls")
    public ResponseEntity<Page<PollResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(pollService.getAllPaged(pageable));
    }

    @GetMapping("/by-question")
    @Operation(summary = "Search polls by question")
    public ResponseEntity<List<PollResponse>> getByQuestion(@RequestParam String question) {
        return ResponseEntity.ok(pollService.findByQuestion(question));
    }

    @GetMapping("/created-within")
    @Operation(summary = "Search polls created within X days")
    public ResponseEntity<List<PollResponse>> getByCreatedWithin(@RequestParam int days) {
        return ResponseEntity.ok(pollService.findByCreatedWithin(days));
    }

    @GetMapping("/expired-within")
    @Operation(summary = "Search polls expired within X days")
    public ResponseEntity<List<PollResponse>> getByExpiredWithin(@RequestParam int days) {
        return ResponseEntity.ok(pollService.findByExpiredWithin(days));
    }
}