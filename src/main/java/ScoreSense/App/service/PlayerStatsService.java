package ScoreSense.App.service;

import java.util.List;
import ScoreSense.App.model.PlayerStats;
public interface PlayerStatsService {
    List<PlayerStats> findAll();
    PlayerStats findById(Long id);
    PlayerStats create(PlayerStats stats);
    PlayerStats update(Long id, PlayerStats stats);
    void delete(Long id);
}
