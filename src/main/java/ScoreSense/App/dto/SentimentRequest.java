package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SentimentRequest {

    @NotBlank
    @Size(max = 50)
    private String source;

    @NotBlank
    @Size(max = 20)
    private String sentiment;

    @NotBlank
    private String comment;

    @NotNull
    private Long teamId;
}
