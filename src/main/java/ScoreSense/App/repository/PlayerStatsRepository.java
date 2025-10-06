package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.PlayerStats;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    
}
