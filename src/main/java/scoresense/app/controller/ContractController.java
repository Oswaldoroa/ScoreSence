package scoresense.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.ContractRequest;
import scoresense.app.dto.ContractResponse;
import scoresense.app.service.ContractService;

@RestController
@RequestMapping("/api/contracts")
@Tag(name = "Contracts", description = "Contract API Endpoints")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    @Operation(summary = "List all contracts")
    public ResponseEntity<List<ContractResponse>> getAll() {
        return ResponseEntity.ok(contractService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contract by ID")
    public ResponseEntity<ContractResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new contract")
    public ResponseEntity<ContractResponse> create(@Valid @RequestBody ContractRequest req) {
        ContractResponse created = contractService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update contract")
    public ResponseEntity<ContractResponse> update(@PathVariable Long id, @Valid @RequestBody ContractRequest req) {
        ContractResponse updated = contractService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete contract")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- MÉTODOS ESPECIALIZADOS ---
    @GetMapping("/paged")
    @Operation(summary = "Page contracts")
    public ResponseEntity<Page<ContractResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(contractService.getAllPaged(pageable));
    }

    @GetMapping("/by-team")
    @Operation(summary = "Search contracts by team")
    public ResponseEntity<List<ContractResponse>> getByTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(contractService.findByTeam(teamId));
    }

    @GetMapping("/by-salary")
    @Operation(summary = "Search contracts by salary")
    public ResponseEntity<List<ContractResponse>> getBySalary(@RequestParam Double salary) {
        return ResponseEntity.ok(contractService.findBySalary(salary));
    }

    @GetMapping("/by-release-clause")
    @Operation(summary = "Search contracts by release clause")
    public ResponseEntity<List<ContractResponse>> getByReleaseClause(@RequestParam Double releaseClause) {
        return ResponseEntity.ok(contractService.findByReleaseClause(releaseClause));
    }
}