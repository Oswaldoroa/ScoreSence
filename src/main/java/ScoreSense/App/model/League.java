package ScoreSense.App.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "season")
    private String season;

    @Column(name = "level")
    private String level;
    
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams;

}
