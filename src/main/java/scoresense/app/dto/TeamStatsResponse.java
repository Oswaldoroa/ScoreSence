package scoresense.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatsResponse {
    private Long teamStatId;
    private Integer possesion;
    private Integer shots;
    private Integer fouls;
    private Integer corners;

    private TeamResponse team; 
    private MatchResponse match;
}