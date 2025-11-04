package ScoreSense.App.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}