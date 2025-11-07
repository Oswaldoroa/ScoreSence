package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
}