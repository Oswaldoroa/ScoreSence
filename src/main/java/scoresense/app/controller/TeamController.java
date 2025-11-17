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
import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.service.TeamService;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "Team API Endpoints")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "List all teams")
    public ResponseEntity<List<TeamResponse>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team by ID")
    public ResponseEntity<TeamResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new team")
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest req) {
        TeamResponse created = teamService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id, @Valid @RequestBody TeamRequest req) {
        TeamResponse updated = teamService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    // --- Métodos especializados ---
    @GetMapping("/paged")
    @Operation(summary = "Page teams")
    public ResponseEntity<Page<TeamResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllPaged(pageable));
    }

    @GetMapping("/search")
    @Operation(summary = "Search teams by name")
    public ResponseEntity<List<TeamResponse>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.findByName(name));
    }

    @GetMapping("/by-league")
    @Operation(summary = "Search teams by league name")
    public ResponseEntity<List<TeamResponse>> searchByLeagueName(@RequestParam String leagueName) {
        return ResponseEntity.ok(teamService.findByLeagueName(leagueName));
    }

    @GetMapping("/by-country")
    @Operation(summary = "Search teams by country")
    public ResponseEntity<List<TeamResponse>> searchByCountry(@RequestParam String country) {
        return ResponseEntity.ok(teamService.findByCountry(country));
    }
}