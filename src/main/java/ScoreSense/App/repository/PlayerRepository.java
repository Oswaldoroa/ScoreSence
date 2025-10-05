package ScoreSense.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ScoreSense.App.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
    @Query(value = "SELECT * FROM players WHERE name = :name", nativeQuery = true)
    List<Player> getPlayersByName(@Param("name") String name);

    @Query(value = "SELECT t FROM teams t WHERE t.name = :name")
    List<Player> getPlayersByNameJPQL(@Param("name") String name);

}