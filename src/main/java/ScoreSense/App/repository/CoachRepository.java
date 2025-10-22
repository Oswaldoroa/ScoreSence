package ScoreSense.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ScoreSense.App.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByNameIgnoreCase(String name);
    List<Coach> findByExperiencedYearsGreaterThan(Integer years);
    Page<Coach> findAll(Pageable pageable);
    List<Coach> findByNationalityIgnoreCase(String nationality);

}