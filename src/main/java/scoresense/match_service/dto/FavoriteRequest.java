package scoresense.match_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FavoriteRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Entity type is required")
    @Pattern(regexp = "^(team|player)$", message = "Entity type must be 'team' or 'player'")
    private String entityType;

    @NotNull(message = "Entity ID is required")
    private Long entityId;
}