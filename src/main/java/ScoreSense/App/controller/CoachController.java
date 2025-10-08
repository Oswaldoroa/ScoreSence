package ScoreSense.App.controller;

import ScoreSense.App.model.Coach;
import ScoreSense.App.repository.CoachRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coaches")

public class CoachController {

    private final CoachRepository coachRepository;
    public CoachController(CoachRepository coachRepository){
        this.coachRepository = coachRepository;
    }

    @GetMapping public List<Coach> getAllCoaches() {
        return coachRepository.findAll(); }

    @GetMapping("/{id}")
    public Coach getCoachById(@PathVariable Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Coach createCoach(@RequestBody Coach coach){
        return  coachRepository.save(coach);
    }

    @PutMapping("/{id}")
    public Coach updateCoach(@PathVariable Long id, @RequestBody Coach coachDetails) {
        return coachRepository.findById(id).map(coach -> {
            coach.setName(coachDetails.getName());
            coach.setNationality(coachDetails.getNationality());
            coach.setExperiencedYears(coachDetails.getExperiencedYears());
            coach.setTeam(coachDetails.getTeam());
            return coachRepository.save(coach);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCoach(@PathVariable Long id) {
        coachRepository.deleteById(id); }

}
