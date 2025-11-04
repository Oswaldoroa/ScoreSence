package ScoreSense.App.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PlayerStatsRequest {
    @PositiveOrZero
    private Integer goals;

    @PositiveOrZero
    private Integer assists;

    @PositiveOrZero
    private Integer yellowCards;

    @PositiveOrZero
    private Integer redCards;

    @PositiveOrZero
    private Integer minutesPlayed;

    @NotNull
    private Long playerId;

    @NotNull
    private Long matchId;
}
