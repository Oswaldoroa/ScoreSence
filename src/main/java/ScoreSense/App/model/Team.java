package ScoreSense.App.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;
    private String country;
    private String league;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;


    public Team() {}

    public Team(String name, String country, String league) {
        this.name = name;
        this.country = country;
        this.league = league;
    }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
}
