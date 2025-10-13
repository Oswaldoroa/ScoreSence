package ScoreSense.App.service;

import java.util.List;
import ScoreSense.App.model.Player;

public interface PlayerService {
    List<Player> findAll();
    Player findById(Long id);
    Player create(Player player);
    Player update(Long id, Player player);
    void delete(Long id);
}
