package scoresense.app.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PollResponse {
    private Long pollId;
    private String question;
    private Long createdByUserId;
    private LocalDateTime createdAt;
}
