package scoresense.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchandiseResponse {
    private Long merchandiseId;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private String imageUrl;
    private String type;
    private Long teamId;
}
