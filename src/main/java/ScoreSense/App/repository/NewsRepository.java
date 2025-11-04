package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    
}
