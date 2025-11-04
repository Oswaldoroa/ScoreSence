package scoresense.app.controller;

import scoresense.app.model.Sentiment;
import scoresense.app.repository.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
@RestController
@RequestMapping("/api/sentiments")

public class SentimentController {
    @Autowired
    private SentimentRepository sentimentRepository;

    @GetMapping
    @Operation(summary = "Get sentiments", description = "Get all sentiments")
    public List<Sentiment> getAllSentiments() {
        return sentimentRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a sentiment", description = "Get a sentiment by ID")
    public ResponseEntity<Sentiment> getSentimentById(@PathVariable Long id) {
        return sentimentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a sentiment", description = "Create a sentiment by ID")
    public Sentiment createSentiment(@RequestBody Sentiment sentiment) {
        return sentimentRepository.save(sentiment);
    }

}
