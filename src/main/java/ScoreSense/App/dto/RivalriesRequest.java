package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class RivalriesRequest {
    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    private Long teamVisitorId;

    @NotNull
    private Long teamLocalId;
}
