package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Sin métodos especializados
}