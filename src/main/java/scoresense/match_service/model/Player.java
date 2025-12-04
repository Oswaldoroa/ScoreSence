package scoresense.match_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "age")
    private int age;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    // CAMBIO CRÍTICO: ID en lugar de objeto Team
    @Column(name = "team_id", nullable = false)
    private Long teamId;
}
