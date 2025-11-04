package scoresense.app.controller;

import scoresense.app.model.News;
import scoresense.app.repository.NewsRepository;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/news")

public class NewsController {

    private final NewsRepository newsRepository;
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    @Operation(summary = "Get news", description = "Get all news")
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a new", description = "Get a news by ID")
    public News getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new", description = "Create a news by ID")
    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new", description = "Update a news by ID")
    public News updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        return newsRepository.findById(id).map(news -> {
            news.setTitle(newsDetails.getTitle());
            news.setContent(newsDetails.getContent());
            news.setAuthor(newsDetails.getAuthor());
            news.setSource_url(newsDetails.getSource_url());
            news.setImage_url(newsDetails.getImage_url());
            news.setPublishDate(newsDetails.getPublishDate());
            news.setTeam(newsDetails.getTeam());
            return newsRepository.save(news); }).orElse(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a new", description = "Delete a news by ID")
    public void deleteNews(@PathVariable Long id)
    {
        newsRepository.deleteById(id);
    }

}
