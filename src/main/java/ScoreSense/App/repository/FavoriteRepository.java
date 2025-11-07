package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Favorite;

public interface FavoriteRepository extends JpaRepository < Favorite, Long> {
}
