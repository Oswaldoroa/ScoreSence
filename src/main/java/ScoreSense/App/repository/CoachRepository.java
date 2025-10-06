package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    
}