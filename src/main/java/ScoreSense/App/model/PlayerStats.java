package ScoreSense.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_stats")
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private Integer minutesPlayed;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;


    public PlayerStats() {}

    public PlayerStats(Integer goals, Integer assists, Integer yellowCards, Integer redCards, Integer minutesPlayed, Player player, Match match) {
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutesPlayed = minutesPlayed;
        this.player = player;
        this.match = match;
    }

    public Long getStatId() { return statId; }
    public void setStatId(Long statId) { this.statId = statId; }
    public Integer getGoals() { return goals; }
    public void setGoals(Integer goals) { this.goals = goals; }
    public Integer getAssists() { return assists; }
    public void setAssists(Integer assists) { this.assists = assists; }
    public Integer getYellowCards() { return yellowCards; }
    public void setYellowCards(Integer yellowCards) { this.yellowCards = yellowCards; }
    public Integer getRedCards() { return redCards; }
    public void setRedCards(Integer redCards) { this.redCards = redCards; }
    public Integer getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(Integer minutesPlayed) { this.minutesPlayed = minutesPlayed; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
}
