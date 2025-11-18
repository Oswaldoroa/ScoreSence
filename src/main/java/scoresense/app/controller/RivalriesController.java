package scoresense.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import scoresense.app.dto.RivalriesRequest;
import scoresense.app.dto.RivalriesResponse;
import scoresense.app.service.RivalriesService;

import java.util.List;

@RestController
@RequestMapping("/api/rivalries")
@Tag(name = "Rivalries", description = "Endpoints for managing team rivalries")
public class RivalriesController {

    private final RivalriesService service;

    public RivalriesController(RivalriesService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all rivalries")
    public ResponseEntity<List<RivalriesResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get rivalry by ID")
    public ResponseEntity<RivalriesResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new rivalry")
    public ResponseEntity<RivalriesResponse> create(@RequestBody RivalriesRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update rivalry")
    public ResponseEntity<RivalriesResponse> update(@PathVariable Long id, @RequestBody RivalriesRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete rivalry")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    @Operation(summary = "Page rivalries")
    public ResponseEntity<Page<RivalriesResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaged(pageable));
    }

    @GetMapping("/search/visitor")
@Operation(summary = "Search rivalries by visitor team name")
public ResponseEntity<List<RivalriesResponse>> searchByVisitorName(@RequestParam String visitorName) {
    return ResponseEntity.ok(service.searchByVisitorName(visitorName));
}

@GetMapping("/search/local")
@Operation(summary = "Search rivalries by local team name")
public ResponseEntity<List<RivalriesResponse>> searchByLocalName(@RequestParam String localName) {
    return ResponseEntity.ok(service.searchByLocalName(localName));
}

@GetMapping("/search/description")
@Operation(summary = "Search rivalries by description")
public ResponseEntity<List<RivalriesResponse>> searchByDescription(@RequestParam String description) {
    return ResponseEntity.ok(service.searchByDescription(description));
}

}