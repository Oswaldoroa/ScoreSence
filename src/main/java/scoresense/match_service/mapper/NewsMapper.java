package scoresense.match_service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import scoresense.match_service.dto.NewsRequest;
import scoresense.match_service.dto.NewsResponse;
import scoresense.match_service.model.News;

public final class NewsMapper {

    public static NewsResponse toResponse(News news) {
        if (news == null) {
            return null;
        }

        return NewsResponse.builder()
                .newsId(news.getNewsId())
                .title(news.getTitle())
                .content(news.getContent())
                .publishDate(news.getPublishDate())
                .author(news.getAuthor())
                .sourceUrl(news.getSourceUrl())
                .imageUrl(news.getImageUrl())
                .teamId(news.getTeamId())                .build();
    }

    public static List<NewsResponse> toResponseList(List<News> newsList) {
        if (newsList == null) {
            return List.of();
        }
        return newsList.stream()
                .map(NewsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static News toEntity(NewsRequest request) {
        if (request == null) {
            return null;
        }

        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setAuthor(request.getAuthor());
        news.setSourceUrl(request.getSourceUrl());
        news.setImageUrl(request.getImageUrl());
        news.setTeamId(request.getTeamId());    
            return news;
    }

    public static void copyToEntity(NewsRequest request, News entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setAuthor(request.getAuthor());
        entity.setSourceUrl(request.getSourceUrl());
        entity.setImageUrl(request.getImageUrl());
        entity.setTeamId(request.getTeamId());    }
}
