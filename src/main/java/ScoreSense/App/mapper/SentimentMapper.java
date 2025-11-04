package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.SentimentRequest;
import ScoreSense.App.dto.SentimentResponse;
import ScoreSense.App.model.Sentiment;

public final class SentimentMapper {

    public static SentimentResponse toResponse(Sentiment sentiment) {
        if (sentiment == null) return null;

        return SentimentResponse.builder()
                .sentimentId(sentiment.getSentimentId())
                .source(sentiment.getSource())
                .sentiment(sentiment.getSentiment())
                .comment(sentiment.getComment())
                .createdAt(sentiment.getCreatedAt())
                .teamId(sentiment.getTeam() != null ? sentiment.getTeam().getTeamId() : null)
                .build();
    }


    public static List<SentimentResponse> toResponseList(List<Sentiment> sentiments) {
        if (sentiments == null) return List.of();
        return sentiments.stream()
                .map(SentimentMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Sentiment toEntity(SentimentRequest request) {
        if (request == null) return null;

        Sentiment sentiment = new Sentiment();
        sentiment.setSource(request.getSource());
        sentiment.setSentiment(request.getSentiment());
        sentiment.setComment(request.getComment());

        return sentiment;
    }


    public static void copyToEntity(SentimentRequest request, Sentiment entity) {
        if (request == null || entity == null) return;

        entity.setSource(request.getSource());
        entity.setSentiment(request.getSentiment());
        entity.setComment(request.getComment());

    }
}
