package ScoreSense.App.controller;

import java.util.List;

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
    @Operation(summary = "Listar todas las ligas", description = "Devuelve una lista de todas las ligas")
    public ResponseEntity<List<LeagueResponse>> getAll() {
        return ResponseEntity.ok(leagueService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener liga por ID", description = "Devuelve los datos de una liga según su ID")
    public ResponseEntity<LeagueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva liga", description = "Crea una nueva liga")
    public ResponseEntity<LeagueResponse> create(@Valid @RequestBody LeagueRequest req) {
        LeagueResponse created = leagueService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una liga", description = "Actualiza la información de una liga existente")
    public ResponseEntity<LeagueResponse> update(@PathVariable Long id, @Valid @RequestBody LeagueRequest req) {
        LeagueResponse updated = leagueService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una liga", description = "Elimina una liga por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}