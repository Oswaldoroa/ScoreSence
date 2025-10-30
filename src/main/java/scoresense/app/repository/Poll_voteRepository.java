package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Poll_vote;
public interface Poll_voteRepository extends JpaRepository<Poll_vote,Long> {
}
