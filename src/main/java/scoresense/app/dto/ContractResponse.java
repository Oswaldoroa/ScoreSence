package scoresense.app.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ContractResponse {
    private Long contractId;
    private Long playerId;
    private Long teamId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double salary;
}
