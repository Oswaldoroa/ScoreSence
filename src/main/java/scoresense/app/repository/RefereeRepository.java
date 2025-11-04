package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Referee;

public interface RefereeRepository extends JpaRepository<Referee,Long> {
}
