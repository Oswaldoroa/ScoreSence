package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
