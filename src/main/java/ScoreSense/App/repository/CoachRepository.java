package scoresense.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.Coach;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByNameIgnoreCase(String name);
    List<Coach> findByExperiencedYearsGreaterThanEqual(Integer years);
    Page<Coach> findAll(Pageable pageable);
    List<Coach> findByNationalityIgnoreCase(String nationality);

}