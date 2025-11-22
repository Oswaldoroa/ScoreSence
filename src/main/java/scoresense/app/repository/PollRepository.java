package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.Poll;

import java.time.LocalDateTime;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Page<Poll> findAll(Pageable pageable);

    List<Poll> findByQuestionContainingIgnoreCase(String question);

    List<Poll> findByCreatedAtAfter(LocalDateTime date);

    List<Poll> findByExpiresAtBefore(LocalDateTime date);
}