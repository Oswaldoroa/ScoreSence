package ScoreSense.App.service;
import java.util.List;
import ScoreSense.App.model.Favorite;

public interface FavoriteService {
    List<Favorite> findAll();
    Favorite findById(Long id);
    Favorite create(Favorite favorite);
    Favorite update(Long id, Favorite favorite);
    void delete(Long id);
}
