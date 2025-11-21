package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.TeamStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {
    List<TeamStats> findByFouls(Integer fouls);
    List<TeamStats> findByShots(Integer shots);
    List<TeamStats> findByTeam_TeamId(Long teamId); 
    Page<TeamStats> findAll(Pageable pageable);
}