package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.ContractRequest;
import scoresense.app.dto.ContractResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.ContractMapper;
import scoresense.app.model.Contract;
import scoresense.app.model.Player;
import scoresense.app.model.Team;
import scoresense.app.repository.ContractRepository;
import scoresense.app.repository.PlayerRepository;
import scoresense.app.repository.TeamRepository;

@Service
@Transactional
public class ContractService {

    private final ContractRepository contractRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public ContractService(ContractRepository contractRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.contractRepository = contractRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<ContractResponse> getAll() {
        return contractRepository.findAll()
                .stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ContractResponse getById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        return ContractMapper.toResponse(contract);
    }

    public ContractResponse create(ContractRequest req) {
        Contract contract = ContractMapper.toEntity(req);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        contract.setTeam(team);

        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", req.getPlayerId()));
        contract.setPlayer(player);

        Contract saved = contractRepository.save(contract);
        return ContractMapper.toResponse(saved);
    }

    public ContractResponse update(Long id, ContractRequest req) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));

        ContractMapper.copyToEntity(req, contract);

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        contract.setTeam(team);

        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", req.getPlayerId()));
        contract.setPlayer(player);

        Contract updated = contractRepository.save(contract);
        return ContractMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        contractRepository.delete(contract);
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    public Page<ContractResponse> getAllPaged(Pageable pageable) {
        return contractRepository.findAll(pageable)
                .map(ContractMapper::toResponse);
    }

    public List<ContractResponse> findByTeam(Long teamId) {
        return contractRepository.findByTeam_TeamId(teamId)
                .stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ContractResponse> findBySalary(Double salary) {
        return contractRepository.findBySalaryGreaterThanEqual(salary)
                .stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ContractResponse> findByReleaseClause(Double releaseClause) {
        return contractRepository.findByReleaseClauseLessThanEqual(releaseClause)
                .stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }
}