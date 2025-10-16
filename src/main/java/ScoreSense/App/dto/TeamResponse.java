package ScoreSense.App.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    @JsonProperty("team_id")
    Long teamId;
    @JsonProperty("name")
    String name;
    @JsonProperty("country")
    String country;
    @JsonProperty("founded_year")
    String foundedYear;
    @JsonProperty("stadium")
    String stadium;
    @JsonProperty("logo_url")
    String logoUrl;
    @JsonProperty("player_ids")
    List<Long> playerIds;
    @JsonProperty("sentiment_ids")
    List<Long> sentimentIds;
    @JsonProperty("league_id")
    Long leagueId;
    @JsonProperty("coach_id")
    Long coachId;
}
