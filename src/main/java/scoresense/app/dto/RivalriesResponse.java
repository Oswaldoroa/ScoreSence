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
public class RivalriesResponse {

    @JsonProperty("rivalrie_id")
    Long rivalrieId;
    @JsonProperty("description")
    String description;
    @JsonProperty("team_visitor_id")
    Long teamVisitorId;
    @JsonProperty("team_local_id")
    Long teamLocalId;
}
