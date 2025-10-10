package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MatchDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String matchDate;
    private Integer homeScore;
    private Integer awayScore;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }
    public Integer getHomeScore() {
        return homeScore;
    }
    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }
    public Integer getAwayScore() {
        return awayScore;
    }
    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}
