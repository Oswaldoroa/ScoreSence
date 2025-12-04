package scoresense.match_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}