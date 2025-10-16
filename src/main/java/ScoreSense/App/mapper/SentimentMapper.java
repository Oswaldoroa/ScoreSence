package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.SentimentsDTO;
import ScoreSense.App.model.Sentiment;

public final class SentimentMapper {

    public static SentimentsDTO toDTO(Sentiment sentiment) {
        if (sentiment == null) {
            return null;
        }

        SentimentsDTO dto = new SentimentsDTO();

        dto.setId(sentiment.getSentimentId());
        dto.setSource(sentiment.getSource());
        dto.setSentiment(sentiment.getSentiment());
        dto.setComment(sentiment.getComment());
        dto.setCreatedAt(sentiment.getCreatedAt());

        if (sentiment.getTeam() != null) {
            dto.setTeamId(sentiment.getTeam().getTeamId());
        }

        return dto;
    }

    public static Sentiment toEntity(SentimentsDTO dto) {
        if (dto == null) {
            return null;
        }

        Sentiment entity = new Sentiment();

        if (dto.getId() != null) {
            entity.setSentimentId(dto.getId());
        }

        entity.setSource(dto.getSource());
        entity.setSentiment(dto.getSentiment());
        entity.setComment(dto.getComment());

        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }

        return entity;
    }

    public static void copyToEntity(SentimentsDTO dto, Sentiment entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setSource(dto.getSource());
        entity.setSentiment(dto.getSentiment());
        entity.setComment(dto.getComment());

    }

    public static List<SentimentsDTO> toDTOList(List<Sentiment> sentimentsList) {
        if (sentimentsList == null) {
            return List.of();
        }
        return sentimentsList.stream()
                .map(SentimentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
