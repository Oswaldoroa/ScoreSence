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
import scoresense.app.dto.LeagueRequest;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.service.LeagueService;

@RestController
@RequestMapping("/api/leagues")
@Tag(name = "Leagues", description = "League API Endpoints")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    @Operation(summary = "List all leagues")
    public ResponseEntity<List<LeagueResponse>> getAll() {
        return ResponseEntity.ok(leagueService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get league by ID")
    public ResponseEntity<LeagueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new league")
    public ResponseEntity<LeagueResponse> create(@Valid @RequestBody LeagueRequest req) {
        LeagueResponse created = leagueService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update league")
    public ResponseEntity<LeagueResponse> update(@PathVariable Long id, @Valid @RequestBody LeagueRequest req) {
        LeagueResponse updated = leagueService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    // --- Método especializado: paginación ---
    @GetMapping("/paged")
    @Operation(summary = "Page leagues")
    public ResponseEntity<Page<LeagueResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(leagueService.getAllPaged(pageable));
    }
}