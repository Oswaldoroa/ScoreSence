package scoresense.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import scoresense.app.dto.RivalriesRequest;
import scoresense.app.dto.RivalriesResponse;
import scoresense.app.mapper.RivalriesMapper;
import scoresense.app.model.Rivalries;
import scoresense.app.model.Team;
import scoresense.app.repository.RivalriesRepository;
import scoresense.app.repository.TeamRepository;

import java.util.List;

@Service
public class RivalriesService {

    private final RivalriesRepository repository;
    private final TeamRepository teamRepository;

    public RivalriesService(RivalriesRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    public List<RivalriesResponse> getAll() {
        return repository.findAll().stream()
                .map(RivalriesMapper::toResponse)
                .toList();
    }

    public RivalriesResponse getById(Long id) {
        return repository.findById(id)
                .map(RivalriesMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Rivalry not found"));
    }

    public RivalriesResponse create(RivalriesRequest req) {
        Team visitor = teamRepository.findById(req.getTeamVisitorId())
                .orElseThrow(() -> new RuntimeException("Visitor team not found"));
        Team local = teamRepository.findById(req.getTeamLocalId())
                .orElseThrow(() -> new RuntimeException("Local team not found"));

        Rivalries rivalry = RivalriesMapper.toEntity(req, visitor, local);
        return RivalriesMapper.toResponse(repository.save(rivalry));
    }

    public RivalriesResponse update(Long id, RivalriesRequest req) {
        Rivalries rivalry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rivalry not found"));

        Team visitor = teamRepository.findById(req.getTeamVisitorId())
                .orElseThrow(() -> new RuntimeException("Visitor team not found"));
        Team local = teamRepository.findById(req.getTeamLocalId())
                .orElseThrow(() -> new RuntimeException("Local team not found"));

        RivalriesMapper.copyToEntity(req, rivalry, visitor, local);
        return RivalriesMapper.toResponse(repository.save(rivalry));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<RivalriesResponse> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(RivalriesMapper::toResponse);
    }

    public List<RivalriesResponse> searchByVisitorName(String visitorName) {
        return repository.findByTeamVisitor_NameIgnoreCase(visitorName)
                .stream()
                .map(RivalriesMapper::toResponse)
                .toList();
    }

    public List<RivalriesResponse> searchByLocalName(String localName) {
        return repository.findByTeamLocal_NameIgnoreCase(localName)
                .stream()
                .map(RivalriesMapper::toResponse)
                .toList();
    }

    public List<RivalriesResponse> searchByDescription(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description)
                .stream()
                .map(RivalriesMapper::toResponse)
                .toList();
    }
}