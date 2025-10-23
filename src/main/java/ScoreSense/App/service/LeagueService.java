package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LeagueResponse getById(Long id) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));
        return toResponse(league);
    }

    public LeagueResponse create(LeagueRequest req) {
        League league = new League();
        league.setName(req.getName());
        league.setCountry(req.getCountry());
        league.setSeason(req.getSeason());
        league.setLevel(req.getLevel());

        League saved = leagueRepository.save(league);
        return toResponse(saved);
    }

    public LeagueResponse update(Long id, LeagueRequest req) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));

        league.setName(req.getName());
        league.setCountry(req.getCountry());
        league.setSeason(req.getSeason());
        league.setLevel(req.getLevel());

        League updated = leagueRepository.save(league);
        return toResponse(updated);
    }

    public void delete(Long id) {
        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", id));
        leagueRepository.delete(league);
    }

    private LeagueResponse toResponse(League league) {
        return LeagueMapper.toResponse(league);
    }
}