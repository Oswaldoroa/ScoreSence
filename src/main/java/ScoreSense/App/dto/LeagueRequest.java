package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LeagueRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String country;

    @NotBlank
    @Size(max = 20)
    private String season;

    @NotBlank
    @Size(max = 20)
    private String level;
}