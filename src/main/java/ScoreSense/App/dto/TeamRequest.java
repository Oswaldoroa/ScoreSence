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
    @Size(max = 50)
    private String country;

    @NotNull
    private Short foundedYear;

    @NotBlank
    @Size(max = 50)
    private String stadium;

    private String logoUrl;

    @NotNull
    private Long leagueId;
}