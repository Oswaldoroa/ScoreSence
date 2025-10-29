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
public class SentimentResponse {

    @JsonProperty("sentiment_id")
    Long sentimentId;
    @JsonProperty("source")
    String source; // Twitter, Facebook, etc.
    @JsonProperty("sentiment")
    String sentiment; // positive, neutral, negative
    @JsonProperty("comment")
    String comment;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
    @JsonProperty("team_id")
    Long teamId;
}
