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
public class FavoriteResponse {

    @JsonProperty("favorite_id")
    Long favoriteId;
    @JsonProperty("entity_type")
    String entity_type;
    @JsonProperty("entity_id")
    Long entity_id;
    @JsonProperty("user_id")
    Long userId;
}
