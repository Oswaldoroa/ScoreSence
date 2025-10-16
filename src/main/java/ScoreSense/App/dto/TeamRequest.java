package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeamRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 60)
    private String country;

    @NotBlank
    @Size(max = 4)
    private String foundedYear;

    @NotBlank
    @Size(max = 100)
    private String stadium;

    @Size(max = 255)
    private String logoUrl;

    @NotNull
    private Long leagueId;

    @NotNull
    private Long coachId;
}
