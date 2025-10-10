package ScoreSense.App.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LeagueDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String name;
    private String country;
    private Integer numberOfTeams;
    private String season;
    private String level;
    private List<TeamDTO> teamsDTO;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }
    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public List<TeamDTO> getTeamsDTO() {
        return teamsDTO;
    }
    public void setTeamsDTO(List<TeamDTO> teamsDTO) {
        this.teamsDTO = teamsDTO;
    }
}
