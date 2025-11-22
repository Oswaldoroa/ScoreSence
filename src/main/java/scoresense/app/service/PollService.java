package scoresense.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.PollMapper;
import scoresense.app.model.Poll;
import scoresense.app.repository.PollRepository;

@Service
@Transactional
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<PollResponse> getAll() {
        return pollRepository.findAll()
                .stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PollResponse getById(Long id) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));
        return PollMapper.toResponse(poll);
    }

    public PollResponse create(PollRequest req) {
        Poll poll = PollMapper.toEntity(req);
        Poll saved = pollRepository.save(poll);
        return PollMapper.toResponse(saved);
    }

    public PollResponse update(Long id, PollRequest req) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));

        PollMapper.copyToEntity(req, poll);
        Poll updated = pollRepository.save(poll);
        return PollMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));
        pollRepository.delete(poll);
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    public Page<PollResponse> getAllPaged(Pageable pageable) {
        return pollRepository.findAll(pageable)
                .map(PollMapper::toResponse);
    }

    public List<PollResponse> findByQuestion(String question) {
        return pollRepository.findByQuestionContainingIgnoreCase(question)
                .stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PollResponse> findByCreatedWithin(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return pollRepository.findByCreatedAtAfter(cutoff)
                .stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PollResponse> findByExpiredWithin(int days) {
        LocalDateTime cutoff = LocalDateTime.now().plusDays(days);
        return pollRepository.findByExpiresAtBefore(cutoff)
                .stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }
}