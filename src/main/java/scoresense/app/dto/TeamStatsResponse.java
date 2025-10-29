package scoresense.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatsResponse {

    @JsonProperty("team_stat_id")
    Long teamStatId;
    @JsonProperty("possession")
    Integer possession;
    @JsonProperty("shots")
    Integer shots;
    @JsonProperty("fouls")
    Integer fouls;
    @JsonProperty("corners")
    Integer corners;
    @JsonProperty("team_id")
    Long teamId;
    @JsonProperty("match_id")
    Long matchId;
}
