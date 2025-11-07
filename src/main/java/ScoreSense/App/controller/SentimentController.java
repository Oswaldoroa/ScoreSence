package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import scoresense.app.model.Sentiment;
import scoresense.app.repository.SentimentRepository;

import java.util.List;
@RestController
@RequestMapping("/api/sentiments")

public class SentimentController {
    @Autowired
    private SentimentRepository sentimentRepository;

    @GetMapping
    public List<Sentiment> getAllSentiments() {
        return sentimentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sentiment> getSentimentById(@PathVariable Long id) {
        return sentimentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sentiment createSentiment(@RequestBody Sentiment sentiment) {
        return sentimentRepository.save(sentiment);
    }

}
