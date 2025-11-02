package scoresense.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.service.PollService;

@RestController
@RequestMapping("/api/polls")
@Tag(name = "Poll", description = "Poll API Endpoints")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    @Operation(summary = "Create new poll")
    public ResponseEntity<PollResponse> create(@Valid @RequestBody PollRequest req) {
        PollResponse created = pollService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Get all polls")
    public ResponseEntity<List<PollResponse>> getAll() {
        return ResponseEntity.ok(pollService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get poll by ID")
    public ResponseEntity<PollResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pollService.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete poll by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pollService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
