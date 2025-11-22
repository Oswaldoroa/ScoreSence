package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.Merchandise;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    Page<Merchandise> findAll(Pageable pageable);

    List<Merchandise> findByTeam_TeamId(Long teamId);

    List<Merchandise> findByNameContainingIgnoreCase(String name);

    List<Merchandise> findByCategoryIgnoreCase(String category);
}