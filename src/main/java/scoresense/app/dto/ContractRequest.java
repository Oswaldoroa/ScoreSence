package scoresense.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ContractRequest {

    @NotNull
    private String entityType;

    @NotNull
    private Long entityId;

    @NotNull
    private Long teamId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Double salary;

    @NotNull
    private Double releaseClause;

    @NotNull
    private Long playerId;
}