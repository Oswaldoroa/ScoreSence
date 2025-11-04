package scoresense.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.service.MerchandiseService;

@RestController
@RequestMapping("/api/merchandise")
@Tag(name = "Merchandise", description = "Merchandise API Endpoints")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @PostMapping
    @Operation(summary = "Create new merchandise")
    public ResponseEntity<MerchandiseResponse> create(@Valid @RequestBody MerchandiseRequest req) {
        MerchandiseResponse created = merchandiseService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Get all merchandise")
    public ResponseEntity<List<MerchandiseResponse>> getAll() {
        return ResponseEntity.ok(merchandiseService.getAll());
    }
}
