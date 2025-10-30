package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Poll;
public interface PollRepository extends JpaRepository<Poll,Long> {
}
