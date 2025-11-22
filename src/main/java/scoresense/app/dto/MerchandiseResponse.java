package scoresense.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseResponse {
    @JsonProperty("merchandise_id")
    private Long merchandiseId;

    @JsonProperty("team")
    private TeamResponse team;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("type")
    private String type;
}