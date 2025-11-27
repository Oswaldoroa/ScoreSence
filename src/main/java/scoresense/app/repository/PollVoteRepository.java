package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.Poll_vote;

import java.time.LocalDateTime;
import java.util.List;

public interface PollVoteRepository extends JpaRepository<Poll_vote, Long> {
    Page<Poll_vote> findAll(Pageable pageable);

    List<Poll_vote> findByUser_UserId(Long userId);

    List<Poll_vote> findByOptionSelectedIgnoreCase(String optionSelected);

    List<Poll_vote> findByVotedAtAfter(LocalDateTime date);
}