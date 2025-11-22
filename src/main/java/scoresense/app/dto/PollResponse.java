package scoresense.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollResponse {
    @JsonProperty("poll_id")
    private Long pollId;

    @JsonProperty("question")
    private String question;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;
}