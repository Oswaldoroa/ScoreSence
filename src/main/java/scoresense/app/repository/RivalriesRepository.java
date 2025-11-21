package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Rivalries;

import java.util.List;

public interface RivalriesRepository extends JpaRepository<Rivalries, Long> {
    List<Rivalries> findByTeamVisitor_NameIgnoreCase(String visitorName);
    List<Rivalries> findByTeamLocal_NameIgnoreCase(String localName);
    List<Rivalries> findByDescriptionContainingIgnoreCase(String description);
}
