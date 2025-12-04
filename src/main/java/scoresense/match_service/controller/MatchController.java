package scoresense.match_service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.match_service.dto.MatchRequest;
import scoresense.match_service.dto.MatchResponse;
import scoresense.match_service.service.MatchService;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Match API Endpoints")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // --- CRUD BÁSICO ---
    @GetMapping
    @Operation(summary = "List all matches", description = "Returns a list of all matches (without pagination)")
    public ResponseEntity<List<MatchResponse>> getAll() {

        return ResponseEntity.ok(matchService.getAllPaged(Pageable.unpaged()).getContent());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match by ID", description = "Return match by using ID")
    public ResponseEntity<MatchResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new match", description = "Create a new match with the provided information")
    public ResponseEntity<MatchResponse> create(@Valid @RequestBody MatchRequest req) {
        MatchResponse created = matchService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/by-home-team")
    @Operation(summary = "Search matches by home team", description = "Search matches where the specified team played as home team")
    public ResponseEntity<List<MatchResponse>> getByHomeTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(matchService.findByHomeTeam(teamId));
    }

    @GetMapping("/by-away-team")
    @Operation(summary = "Search matches by away team", description = "Search matches where the specified team played as away team")
    public ResponseEntity<List<MatchResponse>> getByAwayTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(matchService.findByAwayTeam(teamId));
    }

    @GetMapping("/paged")
    @Operation(summary = "Page matches", description = "Get a paginated list of matches")
    public ResponseEntity<Page<MatchResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(matchService.getAllPaged(pageable));
    }
}
