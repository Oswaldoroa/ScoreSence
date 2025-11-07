package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<TrendingTopic> getAllTrendingTopics() {
        return trendingTopicRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TrendingTopic> getTrendingTopicById(@PathVariable Long id) {
        return trendingTopicRepository.findById(id);
    }

    @PostMapping
    public TrendingTopic createTrendingTopic(@RequestBody TrendingTopic trendingTopic) {
        return trendingTopicRepository.save(trendingTopic);
    }

    @PutMapping("/{id}")
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
    public void deleteTrendingTopic(@PathVariable Long id) {
        trendingTopicRepository.deleteById(id);
    }
}
