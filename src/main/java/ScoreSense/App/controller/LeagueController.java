package scoresense.app.controller;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import scoresense.app.dto.LeagueRequest;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.service.LeagueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leagues")
@Tag(name = "Leagues", description = "Operaciones CRUD sobre ligas")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    @Operation(summary = "Get all leagues", description = "Get a list with all the leagues")
    public ResponseEntity<Page<LeagueResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(leagueService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a league by ID", description = "Get a league by its ID")
    public ResponseEntity<LeagueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a league", description = "Create a new league")
    public ResponseEntity<LeagueResponse> create(@Valid @RequestBody LeagueRequest req) {
        LeagueResponse created = leagueService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a league", description = "Update league information by ID")
    public ResponseEntity<LeagueResponse> update(@PathVariable Long id, @Valid @RequestBody LeagueRequest req) {
        LeagueResponse updated = leagueService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a league", description = "Delete a league by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}