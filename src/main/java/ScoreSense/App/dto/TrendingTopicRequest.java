package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class TrendingTopicRequest {
    @NotBlank
    @Size(max = 50)
    private String socialMedia; // Ejemplo: Twitter, Facebook, etc.

    @NotBlank
    @Size(max = 100)
    private String topic;
}
