package scoresense.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import scoresense.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
