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
    private String founded_year;
    private String stadium;
    private String logo_url;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sentiment> sentiments;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

}
