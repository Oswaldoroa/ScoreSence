package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlayerRequest {
    @NotBlank
    @Size(max = 50)
    private String position;

    @Positive
    private int age;

    @NotBlank
    @Size(max = 60)
    private String nationality;

    @Positive
    private int height;

    @Positive
    private int weight;

    @NotNull
    private Long teamId;
}
