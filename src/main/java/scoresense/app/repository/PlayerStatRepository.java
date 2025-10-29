package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.PlayerStats;

public interface PlayerStatRepository extends JpaRepository<PlayerStats, Long> {
    
}
