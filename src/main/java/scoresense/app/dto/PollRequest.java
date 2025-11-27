package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PollRequest {

    @NotBlank
    private String question;

    @NotNull
    private LocalDateTime expiresAt;
}