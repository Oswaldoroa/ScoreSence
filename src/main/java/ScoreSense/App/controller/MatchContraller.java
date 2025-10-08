package ScoreSense.App.controller;

import ScoreSense.App.repository.MatchRepository;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")

public class MatchContraller {

    private final MatchRepository matchRepository;

    public MatchContraller(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    @GetMapping
    public  List<DataFormatReaders.Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public DataFormatReaders.Match getMatchById(@PathVariable Long id)
    {
        return matchRepository.findById(id).orElse(null);
    }

    //@PostMapping
    //public Match createMatch(@RequestBody Match match) {
        //return matchRepository.save(match);
    //}//

}
