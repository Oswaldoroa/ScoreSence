package scoresense.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RivalriesResponse {
    private Long rivalrieId;
    private String description;
    private TeamResponse teamVisitor;
    private TeamResponse teamLocal;
}
