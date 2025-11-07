package scoresense.app.service;

import java.util.List;

import scoresense.app.model.TeamStats;
public interface TeamStatsService {
    List<TeamStats> findAll();
    TeamStats findById(Long id);
    TeamStats create(TeamStats stats);
    TeamStats update(Long id, TeamStats stats);
    void delete(Long id);
}
