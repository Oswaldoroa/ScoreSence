package ScoreSense.App.service;

import java.util.List;
import ScoreSense.App.model.League;
public interface LeagueService {
    List<League> findAll();

    League findById(Long id);

    League create(League league);

    League update(Long id, League league);

    void delete(Long id);

    List<League> getLeaguesByName(String name);

    List<League> findAll(int page, int pageSize);
}
