package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamStatsDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private Integer possesion;
    private Integer shots;
    private Integer fouls;
    private Integer corners;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPossesion() {
        return possesion;
    }
    public void setPossesion(Integer possesion) {
        this.possesion = possesion;
    }
    public Integer getShots() {
        return shots;
    }
    public void setShots(Integer shots) {
        this.shots = shots;
    }
    public Integer getFouls() {
        return fouls;
    }
    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }
    public Integer getCorners() {
        return corners;
    }
    public void setCorners(Integer corners) {
        this.corners = corners;
    }
}
