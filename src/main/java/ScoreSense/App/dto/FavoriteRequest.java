package ScoreSense.App.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class FavoriteRequest {
    @NotBlank
    @Size(max = 50)
    private String entityType;

    @NotNull
    private Long entityId;

    @NotNull
    private Long userId;
}
