package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.NewsDTO;
import ScoreSense.App.model.News;

public final class NewsMapper {

    public static NewsDTO toDTO(News news) {
        if (news == null) {
            return null;
        }

        NewsDTO dto = new NewsDTO();
        dto.setId(news.getNewsId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setPublishedAt(news.getPublishDate());

        dto.setAuthor(news.getAuthor());
        dto.setSource_url(news.getSource_url());
        dto.setImage_url(news.getImage_url());

        if (news.getTeam() != null) {
            dto.setTeamId(news.getTeam().getTeamId());
        }

        return dto;
    }

    public static News toEntity(NewsDTO dto) {
        if (dto == null) {
            return null;
        }

        News entity = new News();

        if (dto.getId() != null) {
            entity.setNewsId(dto.getId());
        }

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());

        if (dto.getPublishedAt() != null) {
            entity.setPublishDate(dto.getPublishedAt());
        }

        entity.setAuthor(dto.getAuthor());
        entity.setSource_url(dto.getSource_url());
        entity.setImage_url(dto.getImage_url());

        return entity;
    }

    public static void copyToEntity(NewsDTO dto, News entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setAuthor(dto.getAuthor());
        entity.setSource_url(dto.getSource_url());
        entity.setImage_url(dto.getImage_url());
    }

    public static List<NewsDTO> toDTOList(List<News> newsList) {
        if (newsList == null) {
            return List.of();
        }
        return newsList.stream()
                .map(NewsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
