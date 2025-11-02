package scoresense.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    @Operation(summary = "Create new contract")
    public ResponseEntity<ContractResponse> create(@Valid @RequestBody ContractRequest req) {
        ContractResponse created = contractService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Get all contracts")
    public ResponseEntity<List<ContractResponse>> getAll() {
        return ResponseEntity.ok(contractService.getAll());
    }
}
