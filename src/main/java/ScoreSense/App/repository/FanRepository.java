package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Fan;

public interface FanRepository extends JpaRepository<Fan, Long> {
    
}
