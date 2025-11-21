package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAll(Pageable pageable);

    List<Team> findByNameIgnoreCase(String name);

    List<Team> findByLeague_NameIgnoreCase(String leagueName);

    List<Team> findByCountryIgnoreCase(String country);
}