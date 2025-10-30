package scoresense.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Contract;
public interface ContractRepository extends JpaRepository<Contract,Long>{
}
