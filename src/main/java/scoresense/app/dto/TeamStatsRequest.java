package scoresense.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamStatsRequest {
    @NotNull
    private Integer possesion;

    @NotNull
    private Integer shots;

    @NotNull
    private Integer fouls;

    @NotNull
    private Integer corners;

    @NotNull
    private Long teamId;

    @NotNull
    private Long matchId;
}