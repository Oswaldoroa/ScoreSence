package ScoreSense.App.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {

    @JsonProperty("player_id")
    Long playerId;
    @JsonProperty("name")
    String name;
    @JsonProperty("position")
    String position;
    @JsonProperty("age")
    int age;
    @JsonProperty("nationality")
    String nationality;
    @JsonProperty("height")
    int height;
    @JsonProperty("weight")
    int weight;
    @JsonProperty("team_id")
    Long teamId;
}
