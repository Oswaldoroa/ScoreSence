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
    private Long teamId;
}
