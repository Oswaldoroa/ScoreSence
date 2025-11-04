package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
