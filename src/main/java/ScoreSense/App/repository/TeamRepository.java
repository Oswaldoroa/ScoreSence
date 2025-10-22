package ScoreSense.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ScoreSense.App.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.coach IS NULL")
    List<Team> findTeamsWithoutCoach();
}