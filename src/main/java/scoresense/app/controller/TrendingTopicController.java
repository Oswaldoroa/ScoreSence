package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import scoresense.app.model.TrendingTopic;
import scoresense.app.repository.TrendingTopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trending-topics")

public class TrendingTopicController {
    @Autowired
    private TrendingTopicRepository trendingTopicRepository;


    @GetMapping
    @Operation(summary = "Get trending topics", description = "Get all trending topics")
    public List<TrendingTopic> getAllTrendingTopics() {
        return trendingTopicRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a trending topic", description = "Get trending topic by ID")
    public Optional<TrendingTopic> getTrendingTopicById(@PathVariable Long id) {
        return trendingTopicRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a trending topic", description = "Create a new trending topic")
    public TrendingTopic createTrendingTopic(@RequestBody TrendingTopic trendingTopic) {
        return trendingTopicRepository.save(trendingTopic);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a trending topic", description = "Update trending topic by ID")
    public TrendingTopic updateTrendingTopic(@PathVariable Long id, @RequestBody TrendingTopic updatedTopic) {
        return trendingTopicRepository.findById(id)
                .map(topic -> {
                    topic.setSocialMedia(updatedTopic.getSocialMedia());
                    topic.setTopic(updatedTopic.getTopic());
                    topic.setCreatedAt(updatedTopic.getCreatedAt());
                    return trendingTopicRepository.save(topic);
                })
                .orElseGet(() -> {
                    updatedTopic.setTopicId(id);
                    return trendingTopicRepository.save(updatedTopic);
                });
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete trending topic", description = "Delete trending topic by ID")
    public void deleteTrendingTopic(@PathVariable Long id) {
        trendingTopicRepository.deleteById(id);
    }
}
