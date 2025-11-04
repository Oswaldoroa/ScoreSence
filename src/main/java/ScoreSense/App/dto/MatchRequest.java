package ScoreSense.App.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data

public class MatchRequest {
    @NotNull
    private LocalDate matchDate;

    @NotNull
    private Integer homeScore;

    @NotNull
    private Integer awayScore;

    @NotNull
    private Long homeTeamId;

    @NotNull
    private Long awayTeamId;
}
