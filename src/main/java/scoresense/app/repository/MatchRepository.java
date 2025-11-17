package scoresense.app.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByMatchDate(LocalDate matchDate);
    List<Match> findByHomeTeam_TeamId(Long teamId);
    List<Match> findByAwayTeam_TeamId(Long teamId);
}