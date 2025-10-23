package ScoreSense.App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ScoreSense.App.dto.LeagueRequest;
import ScoreSense.App.dto.LeagueResponse;
import ScoreSense.App.exception.ResourceNotFoundException;
import ScoreSense.App.mapper.LeagueMapper;
import ScoreSense.App.model.League;
import ScoreSense.App.repository.LeagueRepository;

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

    public Page<LeagueResponse> getAllPaged(Pageable pageable) {
        return leagueRepository.findAll(pageable)
                .map(LeagueMapper::toResponse);
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