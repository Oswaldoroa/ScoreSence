package scoresense.match_service.controller;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.match_service.dto.NewsRequest;
import scoresense.match_service.dto.NewsResponse;
import scoresense.match_service.service.NewsService;

@RestController
@RequestMapping("/api/news")
@Tag(name = "News", description = "News API Endpoints")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @Operation(summary = "List all news", description = "Returns a list of all news items (without pagination)")
    public ResponseEntity<List<NewsResponse>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @GetMapping("/paged")
    @Operation(summary = "Page news", description = "Get a paginated list of news")
    public ResponseEntity<Page<NewsResponse>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(newsService.getAllPaged(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get news by ID", description = "Return a news item by using ID")
    public ResponseEntity<NewsResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create news", description = "Create a new news item linked to a team")
    public ResponseEntity<NewsResponse> create(@Valid @RequestBody NewsRequest req) {
        NewsResponse created = newsService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update news", description = "Update news information by ID")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @Valid @RequestBody NewsRequest req) {
        NewsResponse updated = newsService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete news", description = "Delete a news item by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-team")
    @Operation(summary = "Search news by team", description = "Returns a list of news items related to a specific team")
    public ResponseEntity<List<NewsResponse>> getByTeam(@RequestParam Long teamId) {
        return ResponseEntity.ok(newsService.findByTeamId(teamId));
    }
}
