package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.model.TrendingTopic;
import scoresense.app.repository.TrendingTopicRepository;

@Service
@Transactional
public class TrendingTopicService {

    private final TrendingTopicRepository trendingTopicRepository;

    public TrendingTopicService(TrendingTopicRepository trendingTopicRepository) {
        this.trendingTopicRepository = trendingTopicRepository;
    }

    public List<TrendingTopicResponse> getAll() {
        return trendingTopicRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TrendingTopicResponse getById(Long id) {
        TrendingTopic topic = trendingTopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));
        return toResponse(topic);
    }

    public TrendingTopicResponse create(TrendingTopicRequest req) {
        TrendingTopic topic = new TrendingTopic();
        topic.setSocialMedia(req.getSocialMedia());
        topic.setTopic(req.getTopic());
        topic.setCreatedAt(req.getCreatedAt());

        TrendingTopic saved = trendingTopicRepository.save(topic);
        return toResponse(saved);
    }

    public TrendingTopicResponse update(Long id, TrendingTopicRequest req) {
        TrendingTopic topic = trendingTopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));

        topic.setSocialMedia(req.getSocialMedia());
        topic.setTopic(req.getTopic());
        topic.setCreatedAt(req.getCreatedAt());

        TrendingTopic updated = trendingTopicRepository.save(topic);
        return toResponse(updated);
    }

    public void delete(Long id) {
        TrendingTopic topic = trendingTopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));
        trendingTopicRepository.delete(topic);
    }

    public Page<TrendingTopicResponse> getAllPaged(Pageable pageable) {
        return trendingTopicRepository.findAll(pageable)
                .map(this::toResponse);
    }

    private TrendingTopicResponse toResponse(TrendingTopic topic) {
        return TrendingTopicResponse.builder()
                .topicId(topic.getTopicId())
                .socialMedia(topic.getSocialMedia())
                .topic(topic.getTopic())
                .createdAt(topic.getCreatedAt())
                .build();
    }
}