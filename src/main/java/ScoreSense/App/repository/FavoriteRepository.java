package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ScoreSense.App.model.Favorite;

public interface FavoriteRepository extends JpaRepository < Favorite, Long> {
}
