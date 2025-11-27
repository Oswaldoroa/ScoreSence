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
public class PollVoteResponse {
    @JsonProperty("poll_vote_id")
    private Long pollVoteId;

    @JsonProperty("poll")
    private PollResponse poll;

    @JsonProperty("user")
    private UserResponse user;

    @JsonProperty("option_selected")
    private String optionSelected;

    @JsonProperty("voted_at")
    private LocalDateTime votedAt;
}