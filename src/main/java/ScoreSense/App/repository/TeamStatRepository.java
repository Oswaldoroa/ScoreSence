package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.TeamStats;

public interface TeamStatRepository extends JpaRepository<TeamStats, Long> {
    
}
