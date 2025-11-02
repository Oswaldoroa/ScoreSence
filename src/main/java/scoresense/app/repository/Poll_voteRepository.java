package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scoresense.app.model.Poll_vote;

import java.util.List;

@Repository
public interface Poll_voteRepository extends JpaRepository<Poll_vote,Long> {

    List<Poll_vote> findByUser_UserId(Long userId);
    List<Poll_vote> findByPoll_PollId(Long pollId);

}
