package ScoreSense.App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coaches")

public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "experienced_years")
    private Integer experiencedYears;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
