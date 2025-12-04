package scoresense.match_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.match_service.dto.PlayerStatsRequest;
import scoresense.match_service.dto.PlayerStatsResponse;
import scoresense.match_service.exception.ResourceNotFoundException;
import scoresense.match_service.mapper.PlayerStatsMapper;
import scoresense.match_service.model.Match;
import scoresense.match_service.model.Player;
import scoresense.match_service.model.PlayerStats;
import scoresense.match_service.repository.MatchRepository;
import scoresense.match_service.repository.PlayerRepository;
import scoresense.match_service.repository.PlayerStatsRepository;

@Service
@Transactional
public class PlayerStatsService {

    private final PlayerStatsRepository playerStatsRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public PlayerStatsService(PlayerStatsRepository playerStatsRepository,
            PlayerRepository playerRepository,
            MatchRepository matchRepository) {
        this.playerStatsRepository = playerStatsRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public List<PlayerStatsResponse> getAll() {
        return playerStatsRepository.findAll()
                .stream()
                .map(PlayerStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public PlayerStatsResponse getById(Long id) {
        PlayerStats playerStats = playerStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlayerStats", "id", id));
        return PlayerStatsMapper.toResponse(playerStats);
    }

    // Crear nuevas estadísticas
    public PlayerStatsResponse create(PlayerStatsRequest req) {
        // 1. Validar y obtener el Player
        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", req.getPlayerId()));

        // 2. Validar y obtener el Match
        Match match = matchRepository.findById(req.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", req.getMatchId()));

        // 3. Mapear DTO a Entidad
        PlayerStats newStats = PlayerStatsMapper.toEntity(req);

        // 4. Asignar las entidades FK
        newStats.setPlayer(player);
        newStats.setMatch(match);

        // 5. Guardar y mapear a Response
        PlayerStats savedStats = playerStatsRepository.save(newStats);
        return PlayerStatsMapper.toResponse(savedStats);
    }

    // --- CONSULTAS ESPECIALIZADAS ---
    // 1. Obtener todos paginado (Consulta especializada paginada)
    public Page<PlayerStatsResponse> getAllPaged(Pageable pageable) {
        return playerStatsRepository.findAll(pageable)
                .map(PlayerStatsMapper::toResponse);
    }

    // 2. Obtener todos los jugadores con al menos una tarjeta roja (sin paginar)
    public List<PlayerStatsResponse> findPlayersWithRedCard() {
        return playerStatsRepository.findByRedCardsGreaterThan(0)
                .stream()
                .map(PlayerStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 3. Obtener todos los jugadores que anotaron mínimo X goles (sin paginar)
    public List<PlayerStatsResponse> findPlayersWithMinGoals(Integer minGoals) {
        return playerStatsRepository.findByGoalsGreaterThanEqual(minGoals)
                .stream()
                .map(PlayerStatsMapper::toResponse)
                .collect(Collectors.toList());
    }
}
