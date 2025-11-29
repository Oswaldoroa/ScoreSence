package scoresense.app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.RefereeRequest;
import scoresense.app.dto.RefereeResponse;
import scoresense.app.service.RefereeService;

@RestController
@RequestMapping("/api/referees")

@Tag(name = "Referees", description = "Referee API Endpoints")
public class RefereeController {

    private final RefereeService refereeService;

    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping
    @Operation(summary = "Get all Referees with pagination", description = "Return a page of referees. Use query params: page, size, and sort.")
    public ResponseEntity<Page<RefereeResponse>> getAllReferees(Pageable pageable) {
        return ResponseEntity.ok(refereeService.findAll(pageable));
    }

    @GetMapping("/search/nationality")
    @Operation(summary = "search referee by nationality", description = "return a list of referees")
    public ResponseEntity<List<RefereeResponse>> findByNationality(@RequestParam String nationality) {
        List<RefereeResponse> referees = refereeService.findByNationality(nationality);
        return ResponseEntity.ok(referees);
    }

    @GetMapping("/search/experience")
    @Operation(summary = "search referee by experience range", description = "retun A list of referees by experiencie years range")
    public ResponseEntity<List<RefereeResponse>> findByExperienceRange(
            @RequestParam int minYears,
            @RequestParam int maxYears) {
        List<RefereeResponse> referees = refereeService.findByExperienceRange(minYears, maxYears);
        return ResponseEntity.ok(referees);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Referee by ID", description = "Return a referee using ID")
    public ResponseEntity<RefereeResponse> getRefereeById(@PathVariable Long id) {
        RefereeResponse referee = refereeService.findById(id);
        return referee != null ? ResponseEntity.ok(referee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new Referee", description = "Create a new referee with the provided information")
    public ResponseEntity<RefereeResponse> createReferee(@RequestBody @Valid RefereeRequest request) {
        RefereeResponse createdReferee = refereeService.create(request);
        return new ResponseEntity<>(createdReferee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Referee", description = "Update referee information by ID")
    public ResponseEntity<RefereeResponse> updateReferee(@PathVariable Long id,
            @RequestBody @Valid RefereeRequest request) {
        RefereeResponse updatedReferee = refereeService.update(id, request);
        return updatedReferee != null ? ResponseEntity.ok(updatedReferee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a Referee", description = "Delete a referee by ID")
    public void deleteReferee(@PathVariable Long id) {
        refereeService.delete(id);
    }
}