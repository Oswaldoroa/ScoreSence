package ScoreSense.App.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ScoreSense.App.dto.TeamRequest;
import ScoreSense.App.dto.TeamResponse;
import ScoreSense.App.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "Operaciones CRUD sobre equipos")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los equipos", description = "Devuelve una lista de todos los equipos")
    public ResponseEntity<List<TeamResponse>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID", description = "Devuelve los datos de un equipo según su ID")
    public ResponseEntity<TeamResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo asociado a una liga existente")
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest req) {
        TeamResponse created = teamService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo", description = "Actualiza la información de un equipo existente")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id, @Valid @RequestBody TeamRequest req) {
        TeamResponse updated = teamService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo", description = "Elimina un equipo por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/without-coach")
@Operation(summary = "List coach without teams", description = "Get all teams that do not have a coach assigned")
public ResponseEntity<List<TeamResponse>> getTeamsWithoutCoach() {
    return ResponseEntity.ok(teamService.findTeamsWithoutCoach());
}
}