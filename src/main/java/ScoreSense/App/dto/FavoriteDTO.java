package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FavoriteDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String entity_type;
    private Long entity_id;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEntity_type() {
        return entity_type;
    }
    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }
    public Long getEntity_id() {
        return entity_id;
    }
    public void setEntity_id(Long entity_id) {
        this.entity_id = entity_id;
    }
}
