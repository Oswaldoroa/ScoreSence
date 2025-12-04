package scoresense.match_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.match_service.dto.NewsRequest;
import scoresense.match_service.dto.NewsResponse;
import scoresense.match_service.exception.ResourceNotFoundException;
import scoresense.match_service.mapper.NewsMapper;
import scoresense.match_service.model.News;
import scoresense.match_service.repository.NewsRepository;

@Service
@Transactional
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsResponse> getAll() {
        return newsRepository.findAll()
                .stream()
                .map(NewsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public NewsResponse getById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", id));
        return NewsMapper.toResponse(news);
    }

    public NewsResponse create(NewsRequest req) {
        News news = NewsMapper.toEntity(req);

        News saved = newsRepository.save(news);
        return NewsMapper.toResponse(saved);
    }

    public NewsResponse update(Long id, NewsRequest req) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", id));

        NewsMapper.copyToEntity(req, existingNews);

        News updated = newsRepository.save(existingNews);
        return NewsMapper.toResponse(updated);
    }

    public void delete(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("News", "id", id);
        }
        newsRepository.deleteById(id);
    }

    public Page<NewsResponse> getAllPaged(Pageable pageable) {
        return newsRepository.findAll(pageable)
                .map(NewsMapper::toResponse);
    }

    public List<NewsResponse> findByTeamId(Long teamId) {

        return newsRepository.findByTeamId(teamId)
                .stream()
                .map(NewsMapper::toResponse)
                .collect(Collectors.toList());
    }
}
