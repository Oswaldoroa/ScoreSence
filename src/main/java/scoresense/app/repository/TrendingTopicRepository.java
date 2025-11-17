package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.TrendingTopic;

import java.time.LocalDateTime;
import java.util.List;

public interface TrendingTopicRepository extends JpaRepository<TrendingTopic, Long> {
    Page<TrendingTopic> findAll(Pageable pageable);

    List<TrendingTopic> findBySocialMediaIgnoreCase(String socialMedia);

    List<TrendingTopic> findByTopicIgnoreCase(String topic);

    List<TrendingTopic> findByCreatedAtAfter(LocalDateTime dateTime);
}