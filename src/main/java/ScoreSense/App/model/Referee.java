package ScoreSense.App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "referees")
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referee_id")
    private Long refereeId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nationality", length = 30)
    private String nationality;

    @Column(name = "experience_years")
    private Integer experienceYears;
}