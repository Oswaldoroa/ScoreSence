package scoresense.app.service;

import java.util.List;

import scoresense.app.model.TrendingTopic;
public interface TrendingTopicService {
    List<TrendingTopic> findAll();
    TrendingTopic findById(Long id);
    TrendingTopic create(TrendingTopic topic);
    TrendingTopic update(Long id, TrendingTopic topic);
    void delete(Long id);
}
