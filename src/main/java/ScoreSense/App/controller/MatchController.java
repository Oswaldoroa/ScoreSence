package scoresense.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import scoresense.app.model.Match;
import scoresense.app.repository.MatchRepository;

@RestController
@RequestMapping("/api/matches")

public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping
    @Operation(summary = "Geet matches", description = "Get all matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a match", description = "Get a match by ID")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    //@PostMapping
    //public Match createMatch(@RequestBody Match match) {
    //return matchRepository.save(match);
    //}//
}
