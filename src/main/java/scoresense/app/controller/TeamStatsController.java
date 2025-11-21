package scoresense.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scoresense.app.dto.TeamStatsRequest;
import scoresense.app.dto.TeamStatsResponse;
import scoresense.app.service.TeamStatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/team-stats")
@Tag(name = "Team Stats", description = "Endpoints for managing team statistics")
public class TeamStatsController {

    private final TeamStatsService teamStatsService;

    public TeamStatsController(TeamStatsService teamStatsService) {
        this.teamStatsService = teamStatsService;
    }

    @GetMapping
    @Operation(summary = "Get all team stats", description = "Retrieve a list of all team statistics records")
    public ResponseEntity<List<TeamStatsResponse>> getAll() {
        return ResponseEntity.ok(teamStatsService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team stats by ID", description = "Retrieve a specific team statistics record by its ID")
    public ResponseEntity<TeamStatsResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamStatsService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create team stats", description = "Create a new team statistics record with the provided data")
    public ResponseEntity<TeamStatsResponse> create(@RequestBody TeamStatsRequest req) {
        return ResponseEntity.ok(teamStatsService.create(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team stats", description = "Update an existing team statistics record by its ID")
    public ResponseEntity<TeamStatsResponse> update(@PathVariable Long id, @RequestBody TeamStatsRequest req) {
        return ResponseEntity.ok(teamStatsService.update(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team stats", description = "Delete a team statistics record by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamStatsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/paged")
    @Operation(summary = "Page team stats", description = "Get a paginated list of team statistics")
    public ResponseEntity<Page<TeamStatsResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(teamStatsService.getAllPaged(pageable));
    }


    // --- Custom Queries ---
    @GetMapping("/by-fouls")
    @Operation(summary = "Find team stats by fouls", description = "Retrieve team statistics records filtered by number of fouls")
    public ResponseEntity<List<TeamStatsResponse>> getByFouls(@RequestParam Integer fouls) {
        return ResponseEntity.ok(teamStatsService.findByFouls(fouls));
    }

    @GetMapping("/by-shots")
    @Operation(summary = "Find team stats by shots", description = "Retrieve team statistics records filtered by number of shots")
    public ResponseEntity<List<TeamStatsResponse>> getByShots(@RequestParam Integer shots) {
        return ResponseEntity.ok(teamStatsService.findByShots(shots));
    }

    @GetMapping("/by-team")
    @Operation(summary = "Find team stats by team", description = "Retrieve team statistics records associated with a specific team ID")
    public ResponseEntity<List<TeamStatsResponse>> getByTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(teamStatsService.findByTeamId(teamId));
    }
}