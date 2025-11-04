package scoresense.app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SponsorshipRequest {
    @NotBlank
    @Size(max = 100)
    private String sponsorName;

    @NotBlank
    @Size(max = 100)
    private String brand;

    @NotNull
    private Long teamId;

    @NotNull
    private Double amount;
}
