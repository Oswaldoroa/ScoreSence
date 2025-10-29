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
public class CoachResponse {
    @JsonProperty("coach_id")
    Long coachId;
    @JsonProperty("name")
    String name;
    @JsonProperty("nationality")
    String nationality;
    @JsonProperty("experienced_years")
    Integer experiencedYears;
    @JsonProperty("team_id")
    Long teamId;
}
