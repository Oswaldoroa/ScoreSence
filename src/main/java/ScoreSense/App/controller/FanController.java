package scoresense.app.controller;
import scoresense.app.model.Fan;
import scoresense.app.repository.FanRepository;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/fans")

public class FanController {
    private final FanRepository fanRepository;

    public  FanController(FanRepository fanRepository){
        this.fanRepository = fanRepository;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a fan", description = "Get a fan by ID")
    public Fan getFanById(@PathVariable Long id){
        return fanRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a fan", description = "Create a fan by ID")
    public Fan createFan(@RequestBody Fan fan) {
        return fanRepository.save(fan);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a fan", description = "Updata a fan by ID")
    public Fan updateFan(@PathVariable Long id, @RequestBody Fan fanDetails) {
        return fanRepository.findById(id).map(fan -> {
            fan.setUsername(fanDetails.getUsername());
            fan.setSocialMedia(fanDetails.getSocialMedia());
            fan.setTeam(fanDetails.getTeam());
            return fanRepository.save(fan);
        }).orElse(null);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a fan", description = "Delete a fan by ID")
    public void deleteFan(@PathVariable Long id) {
        fanRepository.deleteById(id);
    }
}
