package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.TrendingTopic;

public interface TrendingTopicRepository extends JpaRepository<TrendingTopic, Long> {
    
}
