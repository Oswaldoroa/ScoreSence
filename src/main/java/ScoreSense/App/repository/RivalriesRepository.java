package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Rivalries;

public interface RivalriesRepository  extends JpaRepository<Rivalries, Long> {
    
}
