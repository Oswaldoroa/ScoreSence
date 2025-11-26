package scoresense.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.TrendingTopicMapper;
import scoresense.app.model.TrendingTopic;
import scoresense.app.repository.TrendingTopicRepository;
import scoresense.app.service.ia.TopicAnalysisService;

@Service
@Transactional
public class TrendingTopicService {

    private final TrendingTopicRepository repository;
    private final TopicAnalysisService topicAnalysisService;

    public TrendingTopicService(TrendingTopicRepository repository,
                                TopicAnalysisService topicAnalysisService) {
        this.repository = repository;
        this.topicAnalysisService = topicAnalysisService;
    }
<<<<<<< HEAD
    
=======



>>>>>>> 1336442903eb0c3140ef43af9fb253f2c7a25787
    public List<TrendingTopicResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TrendingTopicResponse getById(Long id) {
        TrendingTopic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));
        return TrendingTopicMapper.toResponse(topic);
    }

    public TrendingTopicResponse create(TrendingTopicRequest req) {
        TrendingTopic topic = TrendingTopicMapper.toEntity(req);
        TrendingTopic saved = repository.save(topic);
        return TrendingTopicMapper.toResponse(saved);
    }

    public TrendingTopicResponse update(Long id, TrendingTopicRequest req) {
        TrendingTopic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));

        TrendingTopicMapper.copyToEntity(req, topic);

        TrendingTopic updated = repository.save(topic);
        return TrendingTopicMapper.toResponse(updated);
    }

    // -------- Análisis IA --------
    private TrendingTopicResponse enrichWithEntities(TrendingTopicResponse resp) {
        List<String> persons = topicAnalysisService.detectarPersonas(resp.getTopic());
        resp.setDetectedPersons(persons);
        return resp;
    }

    // --- Métodos especializados ---
    public Page<TrendingTopicResponse> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(TrendingTopicMapper::toResponse);
    }

    public List<TrendingTopicResponse> findBySocialMedia(String socialMedia) {
        return repository.findBySocialMediaIgnoreCase(socialMedia)
                .stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TrendingTopicResponse> findByTopic(String topic) {
        return repository.findByTopicIgnoreCase(topic)
                .stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TrendingTopicResponse> findCreatedWithinDays(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return repository.findByCreatedAtAfter(cutoff)
                .stream()
                .map(TrendingTopicMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        TrendingTopic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrendingTopic", "id", id));
        repository.delete(topic);
    }
}