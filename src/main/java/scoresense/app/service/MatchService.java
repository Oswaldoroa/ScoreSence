package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.MatchRequest;
import scoresense.app.dto.MatchResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.MatchMapper;
import scoresense.app.model.Match;
import scoresense.app.model.Team;
import scoresense.app.repository.MatchRepository;
import scoresense.app.repository.TeamRepository;

@Service
@Transactional
public class MatchService {

    private final MatchRepository repository;
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    // --- CRUD ---
    public List<MatchResponse> getAll() {
        return repository.findAll().stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MatchResponse getById(Long id) {
        Match match = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));
        return MatchMapper.toResponse(match);
    }

    public MatchResponse create(MatchRequest req) {
        Team homeTeam = teamRepository.findById(req.getHomeTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getHomeTeamId()));
        Team awayTeam = teamRepository.findById(req.getAwayTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getAwayTeamId()));

        Match match = MatchMapper.toEntity(req, homeTeam, awayTeam);
        Match saved = repository.save(match);
        return MatchMapper.toResponse(saved);
    }

    public MatchResponse update(Long id, MatchRequest req) {
        Match match = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));

        Team homeTeam = teamRepository.findById(req.getHomeTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getHomeTeamId()));
        Team awayTeam = teamRepository.findById(req.getAwayTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getAwayTeamId()));

        MatchMapper.copyToEntity(req, match, homeTeam, awayTeam);
        Match updated = repository.save(match);
        return MatchMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Match match = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));
        repository.delete(match);
    }

    // --- Specialized methods ---
    public Page<MatchResponse> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(MatchMapper::toResponse);
    }

    public List<MatchResponse> findByDate(LocalDate date) {
        return repository.findByMatchDate(date).stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> findByTeam(Long teamId) {
        return repository.findByHomeTeam_TeamId(teamId).stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }
}