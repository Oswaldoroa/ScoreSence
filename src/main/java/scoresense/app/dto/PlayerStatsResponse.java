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
public class PlayerStatsResponse {

    @JsonProperty("player_stat_id")
    Long playerStatId;
    @JsonProperty("goals")
    Integer goals;
    @JsonProperty("assists")
    Integer assists;
    @JsonProperty("yellow_cards")
    Integer yellowCards;
    @JsonProperty("red_cards")
    Integer redCards;
    @JsonProperty("minutes_played")
    Integer minutesPlayed;
    @JsonProperty("player_id")
    Long playerId;
    @JsonProperty("match_id")
    Long matchId;
}
