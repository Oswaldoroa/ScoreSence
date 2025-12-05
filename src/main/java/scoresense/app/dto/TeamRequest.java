package scoresense.app.dto;

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

    @NotNull
    private Short foundedYear; // usa Short para alinearse con el modelo

    @NotBlank
    private String stadium;

    private String logoUrl;

    @NotNull
    private Long leagueId;
}