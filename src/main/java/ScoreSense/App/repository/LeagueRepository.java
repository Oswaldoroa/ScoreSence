package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ScoreSense.App.model.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
}