package ScoreSense.App.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;
    private String position;
    private int age;
    private String nationality;
    private int height;
    private int weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;


    public Player() {}

    public Player(String name, String position, int age, String nationality, int height, int weight, Team team) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.team = team;
    }


    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
