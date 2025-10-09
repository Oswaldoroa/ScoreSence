package ScoreSense.App.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ScoreSense.App.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
