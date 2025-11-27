package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.ContractRequest;
import scoresense.app.dto.ContractResponse;
import scoresense.app.service.ContractService;

import java.util.List;

@Controller
public class ContractGraphQLController {

    private final ContractService contractService;

    public ContractGraphQLController(ContractService contractService) {
        this.contractService = contractService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<ContractResponse> contracts() {
        return contractService.getAll();
    }

    @QueryMapping
    public ContractResponse contractById(@Argument Long id) {
        return contractService.getById(id);
    }

    @QueryMapping
    public List<ContractResponse> contractsByTeam(@Argument Long teamId) {
        return contractService.findByTeam(teamId);
    }

    @QueryMapping
    public List<ContractResponse> contractsBySalary(@Argument Double salary) {
        return contractService.findBySalary(salary);
    }

    @QueryMapping
    public List<ContractResponse> contractsByReleaseClause(@Argument Double releaseClause) {
        return contractService.findByReleaseClause(releaseClause);
    }

    @QueryMapping
    public List<ContractResponse> pagedContracts(@Argument int page, @Argument int size) {
        return contractService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public ContractResponse createContract(
            @Argument String entityType,
            @Argument Long entityId,
            @Argument Long teamId,
            @Argument String startDate,
            @Argument String endDate,
            @Argument Double salary,
            @Argument Double releaseClause,
            @Argument Long playerId
    ) {
        ContractRequest req = new ContractRequest();
        req.setEntityType(entityType);
        req.setEntityId(entityId);
        req.setTeamId(teamId);
        req.setStartDate(java.time.LocalDate.parse(startDate));
        req.setEndDate(java.time.LocalDate.parse(endDate));
        req.setSalary(salary);
        req.setReleaseClause(releaseClause);
        req.setPlayerId(playerId);
        return contractService.create(req);
    }

    @MutationMapping
    public ContractResponse updateContract(
            @Argument Long contractId,
            @Argument String entityType,
            @Argument Long entityId,
            @Argument Long teamId,
            @Argument String startDate,
            @Argument String endDate,
            @Argument Double salary,
            @Argument Double releaseClause,
            @Argument Long playerId
    ) {
        ContractRequest req = new ContractRequest();
        req.setEntityType(entityType);
        req.setEntityId(entityId);
        req.setTeamId(teamId);
        req.setStartDate(java.time.LocalDate.parse(startDate));
        req.setEndDate(java.time.LocalDate.parse(endDate));
        req.setSalary(salary);
        req.setReleaseClause(releaseClause);
        req.setPlayerId(playerId);
        return contractService.update(contractId, req);
    }

    @MutationMapping
    public String deleteContract(@Argument Long contractId) {
        contractService.delete(contractId);
        return "Contract deleted successfully";
    }
}