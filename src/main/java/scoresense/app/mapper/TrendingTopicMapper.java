package scoresense.app.mapper;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.model.TrendingTopic;

public final class TrendingTopicMapper {

    public static TrendingTopicResponse toResponse(TrendingTopic topic) {
        if (topic == null) return null;

        return TrendingTopicResponse.builder()
                .topicId(topic.getTopicId())
                .socialMedia(topic.getSocialMedia())
                .topic(topic.getTopic())
                .createdAt(topic.getCreatedAt().atOffset(ZoneOffset.UTC))
                .build();
    }

    public static TrendingTopic toEntity(TrendingTopicRequest request) {
        if (request == null) return null;

        TrendingTopic topic = new TrendingTopic();
        topic.setSocialMedia(request.getSocialMedia());
        topic.setTopic(request.getTopic());
        topic.setCreatedAt(java.time.LocalDateTime.now());
        return topic;
    }

    public static void copyToEntity(TrendingTopicRequest request, TrendingTopic entity) {
        if (request == null || entity == null) return;

        entity.setSocialMedia(request.getSocialMedia());
        entity.setTopic(request.getTopic());
    }

    public static List<TrendingTopicResponse> toResponseList(List<TrendingTopic> topics) {
        if (topics == null) return List.of();

        return topics.stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }
}