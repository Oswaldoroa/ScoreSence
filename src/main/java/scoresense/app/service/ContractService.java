package scoresense.app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.ContractRequest;
import scoresense.app.dto.ContractResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.ContractMapper;
import scoresense.app.model.Contract;
import scoresense.app.model.Player;
import scoresense.app.repository.ContractRepository;
import scoresense.app.repository.PlayerRepository;

@Service
@Transactional
public class ContractService {
    private final ContractRepository contractRepository;
    private final PlayerRepository playerRepository;

    public ContractService(ContractRepository contractRepository, PlayerRepository playerRepository) {
        this.contractRepository = contractRepository;
        this.playerRepository = playerRepository;
    }

    public ContractResponse create(ContractRequest req) {
        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", req.getPlayerId()));

        Contract contract = new Contract();
        contract.setStartDate(req.getStartDate());
        contract.setEndDate(req.getEndDate());
        contract.setSalary(req.getSalary());
        contract.setPlayer(player);

        Contract saved = contractRepository.save(contract);
        return ContractMapper.toResponse(saved);
    }

    public ContractResponse getById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        return ContractMapper.toResponse(contract);
    }

    public List<ContractResponse> getAll() {
        return contractRepository.findAll()
                .stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ContractResponse update(Long id, ContractRequest req) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));

        Player player = playerRepository.findById(req.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", req.getPlayerId()));

        contract.setStartDate(req.getStartDate());
        contract.setEndDate(req.getEndDate());
        contract.setSalary(req.getSalary());
        contract.setPlayer(player);

        Contract updated = contractRepository.save(contract);
        return ContractMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", id));
        contractRepository.delete(contract);
    }
}
