package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlayerStats {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private Integer minutesPlayed;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getGoals() {
        return goals;
    }
    public void setGoals(Integer goals) {
        this.goals = goals;
    }
    public Integer getAssists() {
        return assists;
    }
    public void setAssists(Integer assists) {
        this.assists = assists;
    }
    public Integer getYellowCards() {
        return yellowCards;
    }
    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }
    public Integer getRedCards() {
        return redCards;
    }
    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }
    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }
    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
