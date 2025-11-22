package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import scoresense.app.model.Contract;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findAll(Pageable pageable);

    List<Contract> findByTeam_TeamId(Long teamId);

    List<Contract> findBySalaryGreaterThanEqual(Double salary);

    List<Contract> findByReleaseClauseLessThanEqual(Double releaseClause);
}