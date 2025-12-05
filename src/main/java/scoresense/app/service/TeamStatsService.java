package scoresense.app.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scoresense.app.dto.TeamStatsRequest;
import scoresense.app.dto.TeamStatsResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.TeamStatsMapper;
import scoresense.app.model.Match;
import scoresense.app.model.Team;
import scoresense.app.model.TeamStats;
import scoresense.app.repository.MatchRepository;
import scoresense.app.repository.TeamRepository;
import scoresense.app.repository.TeamStatsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamStatsService {

    private final TeamStatsRepository teamStatsRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamStatsService(TeamStatsRepository teamStatsRepository,
                            TeamRepository teamRepository,
                            MatchRepository matchRepository) {
        this.teamStatsRepository = teamStatsRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    // --- CRUD ---
    public List<TeamStatsResponse> getAll() {
        return teamStatsRepository.findAll()
                .stream()
                .map(TeamStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TeamStatsResponse getById(Long id) {
        TeamStats stats = teamStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TeamStats", "id", id));
        return TeamStatsMapper.toResponse(stats);
    }

    public TeamStatsResponse create(TeamStatsRequest req) {
        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        Match match = matchRepository.findById(req.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", req.getMatchId()));

        TeamStats stats = new TeamStats();
        stats.setPossesion(req.getPossesion());
        stats.setShots(req.getShots());
        stats.setFouls(req.getFouls());
        stats.setCorners(req.getCorners());
        stats.setTeam(team);
        stats.setMatch(match);

        return TeamStatsMapper.toResponse(teamStatsRepository.save(stats));
    }

    public TeamStatsResponse update(Long id, TeamStatsRequest req) {
        TeamStats stats = teamStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TeamStats", "id", id));

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        Match match = matchRepository.findById(req.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", req.getMatchId()));

        stats.setPossesion(req.getPossesion());
        stats.setShots(req.getShots());
        stats.setFouls(req.getFouls());
        stats.setCorners(req.getCorners());
        stats.setTeam(team);
        stats.setMatch(match);

        return TeamStatsMapper.toResponse(teamStatsRepository.save(stats));
    }

    public void delete(Long id) {
        TeamStats stats = teamStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TeamStats", "id", id));
        teamStatsRepository.delete(stats);
    }

    public Page<TeamStatsResponse> getAllPaged(Pageable pageable) {
        return teamStatsRepository.findAll(pageable)
                .map(TeamStatsMapper::toResponse);
    }


    // --- Custom Queries ---
    public List<TeamStatsResponse> findByFouls(Integer fouls) {
        return teamStatsRepository.findByFouls(fouls)
                .stream()
                .map(TeamStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TeamStatsResponse> findByShots(Integer shots) {
        return teamStatsRepository.findByShots(shots)
                .stream()
                .map(TeamStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TeamStatsResponse> findByTeamId(Long teamId) {
        return teamStatsRepository.findByTeam_TeamId(teamId)
                .stream()
                .map(TeamStatsMapper::toResponse)
                .collect(Collectors.toList());
    }
}