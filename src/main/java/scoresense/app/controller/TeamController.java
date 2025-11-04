package scoresense.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.service.TeamService;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "CRUD Operations for teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "Get teams", description = "Devuelve una lista de todos los equipos")
    public ResponseEntity<List<TeamResponse>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a team", description = "Devuelve los datos de un equipo específico")
    public ResponseEntity<TeamResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a team", description = "Crea un nuevo equipo")
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest req) {
        TeamResponse created = teamService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a team", description = "Actualiza la información de un equipo por su ID")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id, @Valid @RequestBody TeamRequest req) {
        TeamResponse updated = teamService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a team", description = "Elimina un equipo por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}