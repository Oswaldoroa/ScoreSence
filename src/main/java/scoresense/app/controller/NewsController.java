package scoresense.app.controller;

import org.springframework.web.bind.annotation.*;

import scoresense.app.model.News;
import scoresense.app.repository.NewsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/news")

public class NewsController {

    private final NewsRepository newsRepository;
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @PostMapping
    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    @PutMapping("/{id}")
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
    public void deleteNews(@PathVariable Long id)
    {
        newsRepository.deleteById(id);
    }

}
