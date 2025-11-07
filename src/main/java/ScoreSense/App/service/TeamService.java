package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.TeamMapper;
import scoresense.app.model.League;
import scoresense.app.model.Team;
import scoresense.app.repository.LeagueRepository;
import scoresense.app.repository.TeamRepository;

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
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TeamResponse getById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        return toResponse(team);
    }

    public TeamResponse create(TeamRequest req) {
        Team team = TeamMapper.toEntity(req);

        League league = leagueRepository.findById(req.getLeagueId())
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", req.getLeagueId()));
        team.setLeague(league);

        Team saved = teamRepository.save(team);
        return toResponse(saved);
    }

    public TeamResponse update(Long id, TeamRequest req) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));

        TeamMapper.copyToEntity(req, team);

        League league = leagueRepository.findById(req.getLeagueId())
                .orElseThrow(() -> new ResourceNotFoundException("League", "id", req.getLeagueId()));
        team.setLeague(league);

        Team updated = teamRepository.save(team);
        return toResponse(updated);
    }

    public void delete(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));
        teamRepository.delete(team);
    }

    private TeamResponse toResponse(Team team) {
        return TeamMapper.toResponse(team);
    }
}