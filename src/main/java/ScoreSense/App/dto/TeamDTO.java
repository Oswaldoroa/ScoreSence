package ScoreSense.App.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamDTO {

    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String name;
    private String country;
    private String founded_year;
    private String stadium;
    private String logo_url;
    private List<PlayerDTO> playersDTO;
    private List<SentimentsDTO> sentimentsDTO;
    private Long coachId;
    private Long leagueId;

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

    public String getFounded_year() {
        return founded_year;
    }

    public void setFounded_year(String founded_year) {
        this.founded_year = founded_year;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public List<PlayerDTO> getPlayersDTO() {
        return playersDTO;
    }

    public void setPlayersDTO(List<PlayerDTO> playersDTO) {
        this.playersDTO = playersDTO;
    }

    public List<SentimentsDTO> getSentimentsDTO() {
        return sentimentsDTO;
    }

    public void setSentimentsDTO(List<SentimentsDTO> sentimentsDTO) {
        this.sentimentsDTO = sentimentsDTO;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }
}
