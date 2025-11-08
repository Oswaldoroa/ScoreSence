package scoresense.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.service.TrendingTopicService;

import java.util.List;

@RestController
@RequestMapping("/api/trending-topics")
@Tag(name = "Trending Topics", description = "Trending Topic API Endpoints")
public class TrendingTopicController {

    private final TrendingTopicService trendingTopicService;

    public TrendingTopicController(TrendingTopicService trendingTopicService) {
        this.trendingTopicService = trendingTopicService;
    }

    @GetMapping
    @Operation(summary = "List all trending topics", description = "Returns a list of all trending topics")
    public ResponseEntity<List<TrendingTopicResponse>> getAll() {
        return ResponseEntity.ok(trendingTopicService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trending topic by ID", description = "Returns a trending topic by its ID")
    public ResponseEntity<TrendingTopicResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(trendingTopicService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a trending topic", description = "Creates a new trending topic")
    public ResponseEntity<TrendingTopicResponse> create(@Valid @RequestBody TrendingTopicRequest req) {
        TrendingTopicResponse created = trendingTopicService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update trending topic", description = "Updates a trending topic by ID")
    public ResponseEntity<TrendingTopicResponse> update(@PathVariable Long id, @Valid @RequestBody TrendingTopicRequest req) {
        TrendingTopicResponse updated = trendingTopicService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete trending topic", description = "Deletes a trending topic by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trendingTopicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}