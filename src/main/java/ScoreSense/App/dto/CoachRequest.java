package ScoreSense.App.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CoachRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 60)
    private String nationality;

    @NotNull
    private Integer experiencedYears;

    @NotNull
    private Long teamId;

}
