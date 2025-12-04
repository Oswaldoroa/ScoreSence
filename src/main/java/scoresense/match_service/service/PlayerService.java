package scoresense.match_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.match_service.dto.PlayerRequest;
import scoresense.match_service.dto.PlayerResponse;
import scoresense.match_service.exception.ResourceNotFoundException;
import scoresense.match_service.mapper.PlayerMapper;
import scoresense.match_service.model.Player;
import scoresense.match_service.repository.PlayerRepository;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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

    public PlayerResponse create(PlayerRequest req) {
        // El mapper asigna req.getTeamId() a player.setTeamId()
        Player player = PlayerMapper.toEntity(req);
        Player saved = playerRepository.save(player);
        return PlayerMapper.toResponse(saved);
    }

    public PlayerResponse update(Long id, PlayerRequest req) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));

        PlayerMapper.copyToEntity(req, player);
        // teamId ya se copió en copyToEntity

        Player updated = playerRepository.save(player);
        return PlayerMapper.toResponse(updated);
    }

    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player", "id", id);
        }
        playerRepository.deleteById(id);
    }

    public Page<PlayerResponse> getAllPaged(Pageable pageable) {
        return playerRepository.findAll(pageable)
                .map(PlayerMapper::toResponse);
    }

    public List<PlayerResponse> findByNationality(String nationality) {
        return playerRepository.findByNationality(nationality)
                .stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PlayerResponse> findByPositionAndTeam(String position, Long teamId) {

        return playerRepository.findByPositionAndTeamId(position, teamId)
                .stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PlayerResponse> findByNationalityAndMaxAge(String nationality, Short maxAge) {
        return playerRepository.findByNationalityAndAgeLessThanEqual(nationality, maxAge)
                .stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
