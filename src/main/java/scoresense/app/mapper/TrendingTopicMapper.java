package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.model.TrendingTopic;

public final class TrendingTopicMapper {
    public static TrendingTopicResponse toResponse(TrendingTopic topic) {
        if (topic == null) return null;

        TrendingTopicResponse response = new TrendingTopicResponse();
        response.setTopicId(topic.getTopicId());
        response.setSocialMedia(topic.getSocialMedia());
        response.setTopic(topic.getTopic());
        response.setCreatedAt(topic.getCreatedAt());

        return response;
    }

    public static List<TrendingTopicResponse> toResponseList(List<TrendingTopic> topics) {
        if (topics == null) return List.of();
        return topics.stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static TrendingTopic toEntity(TrendingTopicRequest request) {
        if (request == null) return null;

        TrendingTopic topic = new TrendingTopic();
        topic.setSocialMedia(request.getSocialMedia());
        topic.setTopic(request.getTopic());

        return topic;
    }

    public static void copyToEntity(TrendingTopicRequest request, TrendingTopic entity) {
        if (request == null || entity == null) return;

        entity.setSocialMedia(request.getSocialMedia());
        entity.setTopic(request.getTopic());
    }
}
