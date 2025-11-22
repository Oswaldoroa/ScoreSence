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
import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.service.MerchandiseService;

@RestController
@RequestMapping("/api/merchandise")
@Tag(name = "Merchandise", description = "Merchandise API Endpoints")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping
    @Operation(summary = "List all merchandise")
    public ResponseEntity<List<MerchandiseResponse>> getAll() {
        return ResponseEntity.ok(merchandiseService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get merchandise by ID")
    public ResponseEntity<MerchandiseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(merchandiseService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new merchandise")
    public ResponseEntity<MerchandiseResponse> create(@Valid @RequestBody MerchandiseRequest req) {
        MerchandiseResponse created = merchandiseService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update merchandise")
    public ResponseEntity<MerchandiseResponse> update(@PathVariable Long id, @Valid @RequestBody MerchandiseRequest req) {
        MerchandiseResponse updated = merchandiseService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete merchandise")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        merchandiseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    @GetMapping("/paged")
    @Operation(summary = "Page merchandise")
    public ResponseEntity<Page<MerchandiseResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(merchandiseService.getAllPaged(pageable));
    }

    @GetMapping("/by-team")
    @Operation(summary = "Search merchandise by team")
    public ResponseEntity<List<MerchandiseResponse>> getByTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(merchandiseService.findByTeam(teamId));
    }

    @GetMapping("/by-name")
    @Operation(summary = "Search merchandise by name")
    public ResponseEntity<List<MerchandiseResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(merchandiseService.findByName(name));
    }

    @GetMapping("/by-category")
    @Operation(summary = "Search merchandise by category")
    public ResponseEntity<List<MerchandiseResponse>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(merchandiseService.findByCategory(category));
    }
}