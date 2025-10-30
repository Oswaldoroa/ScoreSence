package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PollRequest {
    @NotBlank
    @Size(max = 150)
    private String question;

    @NotNull
    private Long createdByUserId;
}
