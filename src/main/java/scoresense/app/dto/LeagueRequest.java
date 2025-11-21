package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeagueRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    private String season;

    @NotBlank
    private String level;
}