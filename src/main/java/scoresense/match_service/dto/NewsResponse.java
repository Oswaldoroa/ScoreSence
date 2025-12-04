package scoresense.match_service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {

    @JsonProperty("news_id")
    Long newsId;

    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("publish_date")
    LocalDateTime publishDate;

    @JsonProperty("author")
    String author;

    @JsonProperty("source_url")
    String sourceUrl;

    @JsonProperty("image_url")
    String imageUrl;
    @JsonProperty("team_id")
    Long teamId;
}
