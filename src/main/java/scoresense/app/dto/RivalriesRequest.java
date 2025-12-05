package scoresense.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RivalriesRequest {
    private String description;
    private Long teamVisitorId;
    private Long teamLocalId;
}
