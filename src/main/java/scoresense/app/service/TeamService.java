package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.TeamMapper;
import scoresense.app.model.Team;
import scoresense.app.model.League;
import scoresense.app.repository.TeamRepository;
import scoresense.app.repository.LeagueRepository;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
    }

    public List<TeamResponse> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TeamResponse getById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        return TeamMapper.toResponse(team);
    }

    public TeamResponse create(TeamRequest req) {
        Team team = TeamMapper.toEntity(req);

        League league = leagueRepository.findById(req.getLeagueId())
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", req.getLeagueId()));
        team.setLeague(league);

        Team saved = teamRepository.save(team);
        return TeamMapper.toResponse(saved);
    }

    public TeamResponse update(Long id, TeamRequest req) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));

        TeamMapper.copyToEntity(req, team);

        League league = leagueRepository.findById(req.getLeagueId())
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", req.getLeagueId()));
        team.setLeague(league);

        Team updated = teamRepository.save(team);
        return TeamMapper.toResponse(updated);
    }


    // --- Métodos especializados ---
    public Page<TeamResponse> getAllPaged(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(TeamMapper::toResponse);
    }

    public List<TeamResponse> findByName(String name) {
        return teamRepository.findByNameIgnoreCase(name)
                .stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TeamResponse> findByLeagueName(String leagueName) {
        return teamRepository.findByLeague_NameIgnoreCase(leagueName)
                .stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TeamResponse> findByCountry(String country) {
        return teamRepository.findByCountryIgnoreCase(country)
                .stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }
}