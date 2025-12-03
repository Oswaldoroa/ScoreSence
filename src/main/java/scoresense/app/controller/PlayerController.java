package scoresense.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.PlayerRequest;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.service.PlayerService;
import scoresense.app.service.ia.TopicAnalysisService;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Players", description = "Player API Endpoints")
public class PlayerController {

    private final PlayerService playerService;
    private final TopicAnalysisService topicAnalysisService;

    public PlayerController(PlayerService playerService, TopicAnalysisService topicAnalysisService) {
        this.playerService = playerService;
        this.topicAnalysisService = topicAnalysisService;
    }

    @PostMapping
    @Operation(summary = "Create a new player with Azure AI entity detection")
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest req) {
        PlayerResponse created = playerService.create(req);

        // 2. Analizar con Azure IA (sobre el nombre del jugador)
        List<String> persons = topicAnalysisService.detectarPersonas(req.getName());

        // 3. Agregar a la respuesta
        created.setDetectedPersons(persons);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /* 
    @PostMapping
    @Operation(summary = "Create a new player", description = "Create a new player with the provided information")
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest req) {
        PlayerResponse created = playerService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    */

    @GetMapping
    @Operation(summary = "List all players", description = "Returns a list of all players")
    public ResponseEntity<List<PlayerResponse>> getAll() {
        return ResponseEntity.ok(playerService.getAll());
    }

    //Specialized endpoints
    @GetMapping("/{id}")
    @Operation(summary = "Get player by ID", description = "Return player by using ID")
    public ResponseEntity<PlayerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getById(id));
    }

    

    @PutMapping("/{id}")
    @Operation(summary = "Update player", description = "Update player information by ID")
    public ResponseEntity<PlayerResponse> update(@PathVariable Long id, @Valid @RequestBody PlayerRequest req) {
        PlayerResponse updated = playerService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player", description = "Delete a player by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}