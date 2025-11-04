package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
