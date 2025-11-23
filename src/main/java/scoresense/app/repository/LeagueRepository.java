package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.League;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {
    Page<League> findAll(Pageable pageable);

    List<League> findByNameContainingIgnoreCase(String name);

    List<League> findByCountryIgnoreCase(String country);

    List<League> findBySeason(String season);
}