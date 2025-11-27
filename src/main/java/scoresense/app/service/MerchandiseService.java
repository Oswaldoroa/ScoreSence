package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.MerchandiseMapper;
import scoresense.app.model.Merchandise;
import scoresense.app.model.Team;
import scoresense.app.repository.MerchandiseRepository;
import scoresense.app.repository.TeamRepository;

@Service
@Transactional
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final TeamRepository teamRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository, TeamRepository teamRepository) {
        this.merchandiseRepository = merchandiseRepository;
        this.teamRepository = teamRepository;
    }

    public List<MerchandiseResponse> getAll() {
        return merchandiseRepository.findAll()
                .stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MerchandiseResponse getById(Long id) {
        Merchandise merchandise = merchandiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Merchandise", "id", id));
        return MerchandiseMapper.toResponse(merchandise);
    }

    public MerchandiseResponse create(MerchandiseRequest req) {
        Merchandise merchandise = MerchandiseMapper.toEntity(req);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        merchandise.setTeam(team);

        Merchandise saved = merchandiseRepository.save(merchandise);
        return MerchandiseMapper.toResponse(saved);
    }

    public MerchandiseResponse update(Long id, MerchandiseRequest req) {
        Merchandise merchandise = merchandiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Merchandise", "id", id));

        MerchandiseMapper.copyToEntity(req, merchandise);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        merchandise.setTeam(team);

        Merchandise updated = merchandiseRepository.save(merchandise);
        return MerchandiseMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Merchandise merchandise = merchandiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Merchandise", "id", id));
        merchandiseRepository.delete(merchandise);
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    public Page<MerchandiseResponse> getAllPaged(Pageable pageable) {
        return merchandiseRepository.findAll(pageable)
                .map(MerchandiseMapper::toResponse);
    }

    public List<MerchandiseResponse> findByTeam(Long teamId) {
        return merchandiseRepository.findByTeam_TeamId(teamId)
                .stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<MerchandiseResponse> findByName(String name) {
        return merchandiseRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<MerchandiseResponse> findByCategory(String category) {
        return merchandiseRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }
}