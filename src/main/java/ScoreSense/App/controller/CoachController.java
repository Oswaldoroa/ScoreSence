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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ScoreSense.App.dto.CoachRequest;
import ScoreSense.App.dto.CoachResponse;
import ScoreSense.App.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coaches")
@Tag(name = "Coaches", description = "Coach API Endpoints")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Coach by ID", description = "Return coach by using ID")
    public ResponseEntity<CoachResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new coach", description = "Create a new coach with the provided information")
    public ResponseEntity<CoachResponse> create(@Valid @RequestBody CoachRequest req) {
        CoachResponse created = coachService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update coach", description = "Update coach information by ID")
    public ResponseEntity<CoachResponse> update(@PathVariable Long id, @Valid @RequestBody CoachRequest req) {
        CoachResponse updated = coachService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a coach", description = "Delete a coach by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search coaches by name")
    public ResponseEntity<List<CoachResponse>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(coachService.findByName(name));
    }

    @GetMapping("/experienced")
    @Operation(summary = "Buscar coaches con experiencia mínima")
    public ResponseEntity<List<CoachResponse>> searchByExperience(@RequestParam int years) {
        return ResponseEntity.ok(coachService.findExperiencedCoaches(years));
    }


    @GetMapping("/paged")
    public ResponseEntity<Page<CoachResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(coachService.getAllPaged(pageable));
    }
    @GetMapping("/by-nationality")
    @Operation(summary = "Buscar coaches por nacionalidad")
    public ResponseEntity<List<CoachResponse>> getByNationality(@RequestParam String nationality) {
        return ResponseEntity.ok(coachService.findByNationality(nationality));
    }

}
