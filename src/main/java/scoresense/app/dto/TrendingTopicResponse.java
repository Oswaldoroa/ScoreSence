package scoresense.app.dto;

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
public class TrendingTopicResponse {

    @JsonProperty("topic_id")
    Long topicId;

    @JsonProperty("social_media")
    String socialMedia;

    @JsonProperty("topic")
    String topic;

    @JsonProperty("created_at")
    LocalDateTime createdAt;
}
