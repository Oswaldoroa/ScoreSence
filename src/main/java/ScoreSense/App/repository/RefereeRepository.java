package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ScoreSense.App.model.Referee;

@Repository
public interface  RefereeRepository extends JpaRepository<Referee, Long> {
    
}
