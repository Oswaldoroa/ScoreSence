package scoresense.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import scoresense.app.model.Rivalries;
import scoresense.app.repository.RivalriesRepository;

import java.util.List;

@RestController
@RequestMapping("/api/rivalries")

public class RivalriesController {

    private final RivalriesRepository rivalriesRepository;

    public RivalriesController(RivalriesRepository rivalriesRepository) {
        this.rivalriesRepository = rivalriesRepository;
    }
    @GetMapping
    @Operation(summary = "Get rivalries", description = " Get all rivalries")
    public List<Rivalries> getAllRivalries() {
        return rivalriesRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a  rivalrie", description = "Get rivalry by ID")
    public ResponseEntity<Rivalries> getRivalryById(@PathVariable Long id) {
        return rivalriesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create rivalrie", description = "Create a new rivalry")
    public Rivalries createRivalry(@RequestBody Rivalries rivalry) {
        return rivalriesRepository.save(rivalry);
    }

}
