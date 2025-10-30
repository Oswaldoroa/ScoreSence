package scoresense.app.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Poll_voteResponse {
    private Long pollVoteId;
    private Long pollId;
    private Long userId;
    private Boolean voteOption;
    private LocalDateTime votedAt;
}
