package scoresense.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Rivalries> getAllRivalries() {
        return rivalriesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rivalries> getRivalryById(@PathVariable Long id) {
        return rivalriesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rivalries createRivalry(@RequestBody Rivalries rivalry) {
        return rivalriesRepository.save(rivalry);
    }

}
