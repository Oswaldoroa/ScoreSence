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

    private TeamResponse homeTeam;  // objeto completo
    private TeamResponse awayTeam;  // objeto completo
    private String location;        // si tu entidad Match tiene este campo
}