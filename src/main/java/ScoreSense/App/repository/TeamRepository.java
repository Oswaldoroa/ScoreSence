package scoresense.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Team;
import java.util.List;


public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team>findByCountry(String country);

}