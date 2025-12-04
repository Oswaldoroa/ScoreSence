package scoresense.match_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scoresense.match_service.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    Page<News> findAll(Pageable pageable);

    // Busca por ID numérico (Asegúrate que tu entidad News tenga 'teamId')
    List<News> findByTeamId(Long teamId);
}
