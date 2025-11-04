package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Merchandise;
public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {
}
