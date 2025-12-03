package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.PlayerRequest;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.PlayerMapper;
import scoresense.app.model.Player;
import scoresense.app.model.Team;
import scoresense.app.repository.PlayerRepository;
import scoresense.app.repository.TeamRepository;
import scoresense.app.service.ia.TopicAnalysisService;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TopicAnalysisService topicAnalysisService;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, TopicAnalysisService topicAnalysisService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.topicAnalysisService = topicAnalysisService;
    }

    public List<PlayerResponse> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PlayerResponse getById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        return PlayerMapper.toResponse(player);
    }

    // -------- Análisis IA --------
    private PlayerResponse enrichWithEntities(PlayerResponse resp) {
        List<String> persons = topicAnalysisService.detectarPersonas(resp.getName());
        resp.setDetectedPersons(persons);
        return resp;
    }

    public PlayerResponse create(PlayerRequest req) {
        Player player = PlayerMapper.toEntity(req);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        player.setTeam(team);

        Player saved = playerRepository.save(player);
        return PlayerMapper.toResponse(saved);
    }

    public PlayerResponse update(Long id, PlayerRequest req) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));

        PlayerMapper.copyToEntity(req, player);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        player.setTeam(team);

        Player updated = playerRepository.save(player);
        return PlayerMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
        playerRepository.delete(player);
    }
}