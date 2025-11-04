package scoresense.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}