package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.Sentiment;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
    
}
