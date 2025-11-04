package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.NewsRequest;
import ScoreSense.App.dto.NewsResponse;
import ScoreSense.App.model.News;

public final class NewsMapper {
    public static NewsResponse toResponse(News news) {
        if (news == null) return null;

        return NewsResponse.builder()
                .newsId(news.getNewsId())
                .title(news.getTitle())
                .content(news.getContent())
                .publishDate(news.getPublishDate())
                .author(news.getAuthor())
                .sourceUrl(news.getSource_url())
                .imageUrl(news.getImage_url())
                .teamId(news.getTeam() != null ? news.getTeam().getTeamId() : null)
                .build();
    }


    public static List<NewsResponse> toResponseList(List<News> newsList) {
        if (newsList == null) return List.of();
        return newsList.stream()
                .map(NewsMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static News toEntity(NewsRequest request) {
        if (request == null) return null;

        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setAuthor(request.getAuthor());
        news.setSource_url(request.getSourceUrl());
        news.setImage_url(request.getImageUrl());
        return news;
    }


    public static void copyToEntity(NewsRequest request, News entity) {
        if (request == null || entity == null) return;

        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setAuthor(request.getAuthor());
        entity.setSource_url(request.getSourceUrl());
        entity.setImage_url(request.getImageUrl());

    }
}
