package scoresense.app.controller;
import org.springframework.web.bind.annotation.*;

import scoresense.app.model.Fan;
import scoresense.app.repository.FanRepository;

@RestController
@RequestMapping("/api/fans")

public class FanController {
    private final FanRepository fanRepository;

    public  FanController(FanRepository fanRepository){
        this.fanRepository = fanRepository;
    }
    @GetMapping("/{id}")
    public Fan getFanById(@PathVariable Long id){
        return fanRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Fan createFan(@RequestBody Fan fan) {
        return fanRepository.save(fan);
    }

    @PutMapping("/{id}")
    public Fan updateFan(@PathVariable Long id, @RequestBody Fan fanDetails) {
        return fanRepository.findById(id).map(fan -> {
            fan.setUsername(fanDetails.getUsername());
            fan.setSocialMedia(fanDetails.getSocialMedia());
            fan.setTeam(fanDetails.getTeam());
            return fanRepository.save(fan);
        }).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void deleteFan(@PathVariable Long id) {
        fanRepository.deleteById(id);
    }
}
