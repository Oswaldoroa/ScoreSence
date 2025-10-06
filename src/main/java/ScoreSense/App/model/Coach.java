package ScoreSense.App.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coaches")

public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;

    private String name;
    private String nationality;
    private Integer experiencedYears;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Coach() {}

    public Coach(String name, String nationality, Integer experiencedYears, Team team) {
        this.name = name;
        this.nationality = nationality;
        this.experiencedYears = experiencedYears;
        this.team = team;
    }


    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Integer getExperiencedYears() { return experiencedYears; }
    public void setExperiencedYears(Integer experiencedYears) { this.experiencedYears = experiencedYears; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
