package scoresense.app.service;
import java.util.List;

import scoresense.app.model.Sentiment;

public interface SentimentService {
    List<Sentiment> findAll();
    Sentiment findById(Long id);
    Sentiment create(Sentiment sentiment);
    Sentiment update(Long id, Sentiment sentiment);
    void delete(Long id);
}
