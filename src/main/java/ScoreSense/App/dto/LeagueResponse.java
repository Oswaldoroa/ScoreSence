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
public class LeagueResponse {

    @JsonProperty("league_id")
    Long leagueId;
    @JsonProperty("name")
    String name;
    @JsonProperty("country")
    String country;
    @JsonProperty("season")
    String season;
    @JsonProperty("level")
    String level;
    @JsonProperty("team_ids")
    List<Long> teamIds;
}
