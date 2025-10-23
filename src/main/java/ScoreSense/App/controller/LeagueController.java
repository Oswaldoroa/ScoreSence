package ScoreSense.App.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ScoreSense.App.dto.LeagueRequest;
import ScoreSense.App.dto.LeagueResponse;
import ScoreSense.App.service.LeagueService;
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
    @Operation(summary = "List all leagues", description = "Returns a list of all leagues")
    public ResponseEntity<List<LeagueResponse>> getAll() {
        return ResponseEntity.ok(leagueService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get league by ID", description = "Returns data from a specific league")
    public ResponseEntity<LeagueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new league", description = "Create a new league")
    public ResponseEntity<LeagueResponse> create(@Valid @RequestBody LeagueRequest req) {
        LeagueResponse created = leagueService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update league", description = "Update league information")
    public ResponseEntity<LeagueResponse> update(@PathVariable Long id, @Valid @RequestBody LeagueRequest req) {
        LeagueResponse updated = leagueService.update(id, req);
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/paged")
    @Operation(summary = "List leagues with pagination", description = "Returns leagues in a paginated format")
    public ResponseEntity<Page<LeagueResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(leagueService.getAllPaged(pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete league", description = "Delete a league by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
