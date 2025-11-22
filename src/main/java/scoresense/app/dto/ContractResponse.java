package scoresense.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
    @JsonProperty("contract_id")
    private Long contractId;

    @JsonProperty("entity_type")
    private String entityType;

    @JsonProperty("entity_id")
    private Long entityId;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("salary")
    private Double salary;

    @JsonProperty("release_clause")
    private Double releaseClause;

    @JsonProperty("team")
    private TeamResponse team;

    @JsonProperty("player")
    private PlayerResponse player;
}