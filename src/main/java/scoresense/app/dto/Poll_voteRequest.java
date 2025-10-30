package scoresense.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Poll_voteRequest {
    @NotNull
    private Long pollId;

    @NotNull
    private Long userId;

    @NotNull
    private Boolean voteOption;
}
