package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import scoresense.app.dto.LeagueRequest;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.LeagueMapper;
import scoresense.app.model.League;
import scoresense.app.repository.LeagueRepository;

@Service
@Transactional
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<LeagueResponse> getAll() {
        return leagueRepository.findAll()
                .stream()
                .map(LeagueMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LeagueResponse getById(Long id) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));
        return LeagueMapper.toResponse(league);
    }

    public LeagueResponse create(LeagueRequest req) {
        League league = LeagueMapper.toEntity(req);
        League saved = leagueRepository.save(league);
        return LeagueMapper.toResponse(saved);
    }

    public LeagueResponse update(Long id, LeagueRequest req) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));

        LeagueMapper.copyToEntity(req, league);

        League updated = leagueRepository.save(league);
        return LeagueMapper.toResponse(updated);
    }

    // --- Método especializado: paginación ---
    public Page<LeagueResponse> getAllPaged(Pageable pageable) {
        return leagueRepository.findAll(pageable)
                .map(LeagueMapper::toResponse);
    }
}