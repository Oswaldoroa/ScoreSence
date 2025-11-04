package ScoreSense.App.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TeamStatsRequest {
    @PositiveOrZero
    private Integer possesion;

    @PositiveOrZero
    private Integer shots;

    @PositiveOrZero
    private Integer fouls;

    @PositiveOrZero
    private Integer corners;

    @NotNull
    private Long teamId;

    @NotNull
    private Long matchId;
}
