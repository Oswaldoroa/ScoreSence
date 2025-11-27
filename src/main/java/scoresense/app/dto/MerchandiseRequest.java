package scoresense.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MerchandiseRequest {

    @NotNull
    private Long teamId;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    private String imageUrl;

    @NotBlank
    private String type;
}