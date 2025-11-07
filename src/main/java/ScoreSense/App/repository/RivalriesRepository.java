package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Rivalries;

public interface RivalriesRepository  extends JpaRepository<Rivalries, Long> {
    
}
