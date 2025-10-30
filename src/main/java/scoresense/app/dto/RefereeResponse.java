package scoresense.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefereeResponse {
    private Long refereeId;
    private String name;
    private String nationality;
    private Integer experienceYears;
    private Long leagueId;
}
