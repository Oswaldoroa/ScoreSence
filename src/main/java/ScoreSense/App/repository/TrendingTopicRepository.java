package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.TrendingTopic;

public interface TrendingTopicRepository extends JpaRepository<TrendingTopic, Long> {
    
}
