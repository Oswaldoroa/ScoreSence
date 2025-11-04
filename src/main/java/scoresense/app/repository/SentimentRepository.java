package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Sentiment;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
    
}
