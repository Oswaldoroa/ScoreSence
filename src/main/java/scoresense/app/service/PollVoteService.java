package scoresense.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.PollVoteRequest;
import scoresense.app.dto.PollVoteResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.PollVoteMapper;
import scoresense.app.model.Poll_vote;
import scoresense.app.model.Poll;
import scoresense.app.model.User;
import scoresense.app.repository.PollVoteRepository;
import scoresense.app.repository.PollRepository;
import scoresense.app.repository.UserRepository;

@Service
@Transactional
public class PollVoteService {

    private final PollVoteRepository pollVoteRepository;
    private final PollRepository pollRepository;
    private final UserRepository userRepository;

    public PollVoteService(PollVoteRepository pollVoteRepository, PollRepository pollRepository, UserRepository userRepository) {
        this.pollVoteRepository = pollVoteRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
    }

    public List<PollVoteResponse> getAll() {
        return pollVoteRepository.findAll()
                .stream()
                .map(PollVoteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PollVoteResponse getById(Long id) {
        Poll_vote vote = pollVoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PollVote", "id", id));
        return PollVoteMapper.toResponse(vote);
    }

    public PollVoteResponse create(PollVoteRequest req) {
        Poll_vote vote = PollVoteMapper.toEntity(req);

        Poll poll = pollRepository.findById(req.getPollId())
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", req.getPollId()));
        vote.setPoll(poll);

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", req.getUserId()));
        vote.setUser(user);

        Poll_vote saved = pollVoteRepository.save(vote);
        return PollVoteMapper.toResponse(saved);
    }

    public PollVoteResponse update(Long id, PollVoteRequest req) {
        Poll_vote vote = pollVoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PollVote", "id", id));

        PollVoteMapper.copyToEntity(req, vote);

        Poll poll = pollRepository.findById(req.getPollId())
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", req.getPollId()));
        vote.setPoll(poll);

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", req.getUserId()));
        vote.setUser(user);

        Poll_vote updated = pollVoteRepository.save(vote);
        return PollVoteMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Poll_vote vote = pollVoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PollVote", "id", id));
        pollVoteRepository.delete(vote);
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    public Page<PollVoteResponse> getAllPaged(Pageable pageable) {
        return pollVoteRepository.findAll(pageable)
                .map(PollVoteMapper::toResponse);
    }

    public List<PollVoteResponse> findByUser(Long userId) {
        return pollVoteRepository.findByUser_UserId(userId)
                .stream()
                .map(PollVoteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PollVoteResponse> findByOptionSelected(String option) {
        return pollVoteRepository.findByOptionSelectedIgnoreCase(option)
                .stream()
                .map(PollVoteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PollVoteResponse> findByVotedWithin(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return pollVoteRepository.findByVotedAtAfter(cutoff)
                .stream()
                .map(PollVoteMapper::toResponse)
                .collect(Collectors.toList());
    }
}