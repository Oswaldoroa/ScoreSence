package scoresense.match_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scoresense.match_service.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Override
    Page<Match> findAll(Pageable pageable);

    List<Match> findByHomeTeamId(Long homeTeamId);

    List<Match> findByAwayTeamId(Long awayTeamId);
}
