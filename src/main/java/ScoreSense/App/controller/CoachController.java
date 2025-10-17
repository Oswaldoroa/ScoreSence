package ScoreSense.App.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ScoreSense.App.dto.CoachRequest;
import ScoreSense.App.dto.CoachResponse;
import ScoreSense.App.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coaches")
@Tag(name = "Coaches", description = "Operaciones CRUD sobre entrenadores")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los coaches", description = "Devuelve una lista de todos los entrenadores")
    public ResponseEntity<List<CoachResponse>> getAll() {
        return ResponseEntity.ok(coachService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener coach por ID", description = "Devuelve los datos de un coach según su ID")
    public ResponseEntity<CoachResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo coach", description = "Crea un nuevo coach asociado a un equipo existente")
    public ResponseEntity<CoachResponse> create(@Valid @RequestBody CoachRequest req) {
        CoachResponse created = coachService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un coach", description = "Actualiza la información de un coach existente")
    public ResponseEntity<CoachResponse> update(@PathVariable Long id, @Valid @RequestBody CoachRequest req) {
        CoachResponse updated = coachService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un coach", description = "Elimina un coach por su ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
