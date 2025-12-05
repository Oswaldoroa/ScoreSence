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
public class TeamResponse {

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;

    @JsonProperty("founded_year")
    private Short foundedYear; // mantiene coherencia con el entity

    @JsonProperty("stadium")
    private String stadium;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("league_id")
    private LeagueResponse league;
}