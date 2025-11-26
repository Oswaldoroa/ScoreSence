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
import scoresense.app.dto.TrendingTopicRequest;
import scoresense.app.dto.TrendingTopicResponse;
import scoresense.app.service.TrendingTopicService;
//import scoresense.app.service.ia.AzureTextAnalyticsService;

@RestController
@RequestMapping("/api/trending-topics")
@Tag(name = "Trending Topics", description = "Trending Topic API Endpoints")
public class TrendingTopicController {

    private final TrendingTopicService trendingTopicService;
    //private final AzureTextAnalyticsService azureTextAnalyticsService;


    public TrendingTopicController(TrendingTopicService trendingTopicService /*, AzureTextAnalyticsService azureTextAnalyticsService*/) {
        this.trendingTopicService = trendingTopicService;
        //this.azureTextAnalyticsService = azureTextAnalyticsService;
    }

    @GetMapping
    @Operation(summary = "List all trending topics")
    public ResponseEntity<List<TrendingTopicResponse>> getAll() {
        return ResponseEntity.ok(trendingTopicService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trending topic by ID")
    public ResponseEntity<TrendingTopicResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(trendingTopicService.getById(id));
    }

    /*
    //Para extraer las entidades usando Azure Text Analytics
    @GetMapping("/{id}/entities")
    @Operation(summary = "Get entities mentioned in a trending topic",
            description = "Uses Azure Text Analytics to extract named entities (persons, teams, organizations) from the topic text.")
    public ResponseEntity<List<String>> getEntities(@PathVariable Long id) {
        TrendingTopicResponse topic = trendingTopicService.getById(id);
        List<String> entities = azureTextAnalyticsService.extractEntities(topic.getTopic());
        return ResponseEntity.ok(entities);
    }
    */

    @PostMapping
    @Operation(summary = "Create a new trending topic")
    public ResponseEntity<TrendingTopicResponse> create(@Valid @RequestBody TrendingTopicRequest req) {
        TrendingTopicResponse created = trendingTopicService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update trending topic")
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

    // --- Specialized methods ---
    @GetMapping("/paged")
    @Operation(summary = "Page trending topics")
    public ResponseEntity<Page<TrendingTopicResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(trendingTopicService.getAllPaged(pageable));
    }

    @GetMapping("/search/social")
    @Operation(summary = "Search trending topics by social media")
    public ResponseEntity<List<TrendingTopicResponse>> findBySocialMedia(@RequestParam String socialMedia) {
        return ResponseEntity.ok(trendingTopicService.findBySocialMedia(socialMedia));
    }

    @GetMapping("/search/topic")
    @Operation(summary = "Search trending topics by topic name")
    public ResponseEntity<List<TrendingTopicResponse>> findByTopic(@RequestParam String topic) {
        return ResponseEntity.ok(trendingTopicService.findByTopic(topic));
    }

    @GetMapping("/created-within")
    @Operation(summary = "Search trending topics created within X days")
    public ResponseEntity<List<TrendingTopicResponse>> findCreatedWithinDays(@RequestParam int days) {
        return ResponseEntity.ok(trendingTopicService.findCreatedWithinDays(days));
    }
}