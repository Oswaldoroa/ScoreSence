package scoresense.app.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class SponsorshipResponse {
    private Long sponsorshipId;
    private String sponsorName;
    private String brand;
    private Long teamId;
    private Double amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
