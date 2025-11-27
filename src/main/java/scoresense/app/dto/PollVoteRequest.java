package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PollVoteRequest {

    @NotNull
    private Long pollId;

    @NotNull
    private Long userId;

    @NotBlank
    private String optionSelected;
}