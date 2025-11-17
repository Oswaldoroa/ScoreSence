package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
    Page<League> findAll(Pageable pageable);
}