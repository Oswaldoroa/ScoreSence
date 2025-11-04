package ScoreSense.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ScoreSense.App.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    
}
