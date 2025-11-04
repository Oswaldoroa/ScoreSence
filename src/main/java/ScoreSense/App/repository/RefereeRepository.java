package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scoresense.app.model.Referee;

@Repository
public interface  RefereeRepository extends JpaRepository<Referee, Long> {
    
}
