package scoresense.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrendingTopicResponse {

    @JsonProperty("topic_id")
    private Long topicId;

    @JsonProperty("social_media")
    private String socialMedia;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("created_at")
    private OffsetDateTime  createdAt;
}