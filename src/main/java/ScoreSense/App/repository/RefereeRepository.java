package scoresense.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scoresense.app.model.Referee;

@Repository
public interface  RefereeRepository extends JpaRepository<Referee, Long> {
    


    List<Referee> findByNationality(String nationality);


    List<Referee> findByExperienceYearsBetween(int minYears, int maxYears);
}
