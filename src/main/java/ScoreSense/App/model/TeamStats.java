package ScoreSense.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_stats")
public class TeamStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamStatId;

    private Integer possesion;
    private Integer shots;
    private Integer fouls;
    private Integer comers;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;


    public TeamStats() {}

    public TeamStats(Integer possesion, Integer shots, Integer fouls, Integer comers, Team team, Match match) {
        this.possesion = possesion;
        this.shots = shots;
        this.fouls = fouls;
        this.comers = comers;
        this.team = team;
        this.match = match;
    }

    public Long getTeamStatId() { return teamStatId; }
    public void setTeamStatId(Long teamStatId) { this.teamStatId = teamStatId; }
    public Integer getPossesion() { return possesion; }
    public void setPossesion(Integer possesion) { this.possesion = possesion; }
    public Integer getShots() { return shots; }
    public void setShots(Integer shots) { this.shots = shots; }
    public Integer getFouls() { return fouls; }
    public void setFouls(Integer fouls) { this.fouls = fouls; }
    public Integer getComers() { return comers; }
    public void setComers(Integer comers) { this.comers = comers; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
}
