package ScoreSense.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ScoreSense.App.model.Coach;
import ScoreSense.App.model.Team;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByNameIgnoreCase(String name);
    @Query("SELECT t FROM Team t WHERE t.coach IS NULL")
    List<Team> findTeamsWithoutCoach();
    List<Coach> findByExperiencedYearsGreaterThan(Integer years);
}