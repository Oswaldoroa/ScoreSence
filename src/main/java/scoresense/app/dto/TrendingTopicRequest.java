package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrendingTopicRequest {

    @NotBlank
    private String socialMedia;

    @NotBlank
    private String topic;
}