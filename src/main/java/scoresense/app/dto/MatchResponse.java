package scoresense.app.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponse {
    private Long matchId;
    private LocalDate matchDate;
    private Integer homeScore;
    private Integer awayScore;
    private Long homeTeamId;
    private Long awayTeamId;
}