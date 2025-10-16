package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.TrendingTopicDTO;
import ScoreSense.App.model.TrendingTopic;

public final class TrendingTopicMapper {

    public static TrendingTopicDTO toDTO(TrendingTopic topic) {
        if (topic == null) {
            return null;
        }

        TrendingTopicDTO dto = new TrendingTopicDTO();

        dto.setId(topic.getTopicId());
        dto.setSocialMedia(topic.getSocialMedia());
        dto.setTopic(topic.getTopic());

        dto.setCreatedAt(topic.getCreatedAt());

        return dto;
    }

    public static TrendingTopic toEntity(TrendingTopicDTO dto) {
        if (dto == null) {
            return null;
        }

        TrendingTopic entity = new TrendingTopic();

        if (dto.getId() != null) {
            entity.setTopicId(dto.getId());
        }

        entity.setSocialMedia(dto.getSocialMedia());
        entity.setTopic(dto.getTopic());

        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }

        return entity;
    }

    public static void copyToEntity(TrendingTopicDTO dto, TrendingTopic entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setSocialMedia(dto.getSocialMedia());
        entity.setTopic(dto.getTopic());

    }

    public static List<TrendingTopicDTO> toDTOList(List<TrendingTopic> topicsList) {
        if (topicsList == null) {
            return List.of();
        }
        return topicsList.stream()
                .map(TrendingTopicMapper::toDTO)
                .collect(Collectors.toList());
    }
}
