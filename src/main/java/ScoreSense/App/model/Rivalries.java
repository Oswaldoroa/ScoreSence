package ScoreSense.App.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rivalries")
public class Rivalries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rivalrie_id")
    private Long rivalrieId;

    @Column(name = "description")
    private String description; // redes sociales

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamVisitorId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamLocalId;   

}
