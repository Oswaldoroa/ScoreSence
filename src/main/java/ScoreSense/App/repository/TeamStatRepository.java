package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.TeamStats;

public interface TeamStatRepository extends JpaRepository<TeamStats, Long> {
    
}
