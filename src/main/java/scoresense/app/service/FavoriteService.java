package scoresense.app.service;
import java.util.List;

import scoresense.app.model.Favorite;

public interface FavoriteService {
    List<Favorite> findAll();
    Favorite findById(Long id);
    Favorite create(Favorite favorite);
    Favorite update(Long id, Favorite favorite);
    void delete(Long id);
}
