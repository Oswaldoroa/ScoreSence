package ScoreSense.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivalries")
public class Rivalries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rivalrieId;

    private String description; // redes sociales

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamVisitorId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamLocalId;   

    public Rivalries() {}

    public Rivalries(String description, Team teamVisitorId, Team teamLocalId) {
        this.description = description;
        this.teamVisitorId = teamVisitorId;
        this.teamLocalId = teamLocalId;
    }

    public Long getRivalrieId() { return rivalrieId; }
    public void setRivalrieId(Long rivalrieId) { this.rivalrieId = rivalrieId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Team getTeamVisitorId() { return teamVisitorId; }
    public void setTeamVisitorId(Team teamVisitorId) { this.teamVisitorId = teamVisitorId; }
    public Team getTeamLocalId() { return teamLocalId; }
    public void setTeamLocalId(Team teamLocalId) { this.teamLocalId = teamLocalId; }
}
