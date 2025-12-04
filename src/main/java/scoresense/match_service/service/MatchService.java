package scoresense.match_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.match_service.dto.MatchRequest;
import scoresense.match_service.dto.MatchResponse;
import scoresense.match_service.exception.ResourceNotFoundException;
import scoresense.match_service.mapper.MatchMapper;
import scoresense.match_service.model.Match;
import scoresense.match_service.repository.MatchRepository;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchResponse getById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", id));
        return MatchMapper.toResponse(match);
    }

    public MatchResponse create(MatchRequest req) {
        // Convertimos el DTO a Entidad. 
        // Como req ya trae los IDs (homeTeamId, etc), el Mapper los asigna directamente.
        Match match = MatchMapper.toEntity(req);

        // Guardamos en la BD local
        Match saved = matchRepository.save(match);
        return MatchMapper.toResponse(saved);
    }

    public Page<MatchResponse> getAllPaged(Pageable pageable) {
        return matchRepository.findAll(pageable)
                .map(MatchMapper::toResponse);
    }

    public List<MatchResponse> findByHomeTeam(Long teamId) {
        return matchRepository.findByHomeTeamId(teamId) // Busca por ID numérico
                .stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> findByAwayTeam(Long teamId) {
        return matchRepository.findByAwayTeamId(teamId) // Busca por ID numérico
                .stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }
}
