package scoresense.app.model;

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
    private String description;

    @ManyToOne
    @JoinColumn(name = "team_visitor_id")
    private Team teamVisitor;

    @ManyToOne
    @JoinColumn(name = "team_local_id")
    private Team teamLocal;
}
