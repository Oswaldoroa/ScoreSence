package ScoreSense.App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ScoreSense.App.dto.TeamRequest;
import ScoreSense.App.dto.TeamResponse;
import ScoreSense.App.exception.ResourceNotFoundException;
import ScoreSense.App.mapper.TeamMapper;
import ScoreSense.App.model.League;
import ScoreSense.App.model.Team;
import ScoreSense.App.repository.LeagueRepository;
import ScoreSense.App.repository.TeamRepository;

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
    public List<TeamResponse> findTeamsWithoutCoach() {
    return teamRepository.findTeamsWithoutCoach()
            .stream()
            .map(TeamMapper::toResponse)
            .collect(Collectors.toList());
}
}