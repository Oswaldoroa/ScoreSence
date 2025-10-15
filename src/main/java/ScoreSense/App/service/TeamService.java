package ScoreSense.App.service;

import java.util.List;
import ScoreSense.App.model.Team;

public interface TeamService {
    List<Team> findAll();
    Team findById(Long id);
    Team create(Team team);
    Team update(Long id, Team team);
    void delete(Long id);
}
