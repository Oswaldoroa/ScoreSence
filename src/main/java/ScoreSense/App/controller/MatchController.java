package ScoreSense.App.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ScoreSense.App.model.Match;
import ScoreSense.App.repository.MatchRepository;

@RestController
@RequestMapping("/api/matches")

public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    //@PostMapping
    //public Match createMatch(@RequestBody Match match) {
    //return matchRepository.save(match);
    //}//
}
