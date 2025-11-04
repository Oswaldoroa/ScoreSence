package ScoreSense.App.service;
import java.util.List;
import ScoreSense.App.model.Sentiment;

public interface SentimentService {
    List<Sentiment> findAll();
    Sentiment findById(Long id);
    Sentiment create(Sentiment sentiment);
    Sentiment update(Long id, Sentiment sentiment);
    void delete(Long id);
}
