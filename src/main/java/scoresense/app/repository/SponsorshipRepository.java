package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Sponsorship;
public interface SponsorshipRepository extends JpaRepository<Sponsorship,Long> {
}
