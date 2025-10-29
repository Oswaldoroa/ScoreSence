package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Fan;

public interface FanRepository extends JpaRepository<Fan, Long> {
    
}
