package scoresense.app.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.service.TrendingTopicService;

@Controller
public class TrendingTopicGraphQLController {

    private final TrendingTopicService trendingTopicService;

    public TrendingTopicGraphQLController(TrendingTopicService trendingTopicService) {
        this.trendingTopicService = trendingTopicService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<TrendingTopicResponse> trendingTopics() {
        return trendingTopicService.getAll();
    }

    @QueryMapping
    public TrendingTopicResponse trendingTopicById(@Argument Long id) {
        return trendingTopicService.getById(id);
    }

    @QueryMapping
    public List<TrendingTopicResponse> pagedTrendingTopics(@Argument int page, @Argument int size) {
        return trendingTopicService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    @QueryMapping
    public List<TrendingTopicResponse> searchTrendingTopicsBySocialMedia(@Argument String socialMedia) {
        return trendingTopicService.findBySocialMedia(socialMedia);
    }

    @QueryMapping
    public List<TrendingTopicResponse> searchTrendingTopicsByTopic(@Argument String topic) {
        return trendingTopicService.findByTopic(topic);
    }

    @QueryMapping
    public List<TrendingTopicResponse> trendingTopicsCreatedWithinDays(@Argument int days) {
        return trendingTopicService.findCreatedWithinDays(days);
    }

    // --- MUTATIONS ---
    @MutationMapping
    public TrendingTopicResponse createTrendingTopic(
            @Argument String socialMedia,
            @Argument String topic
    ) {
        TrendingTopicRequest req = new TrendingTopicRequest();
        req.setSocialMedia(socialMedia);
        req.setTopic(topic);
        return trendingTopicService.create(req);
    }

    @MutationMapping
    public TrendingTopicResponse updateTrendingTopic(
            @Argument Long topicId,
            @Argument String socialMedia,
            @Argument String topic
    ) {
        TrendingTopicRequest req = new TrendingTopicRequest();
        req.setSocialMedia(socialMedia);
        req.setTopic(topic);
        return trendingTopicService.update(topicId, req);
    }
}