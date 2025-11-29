package scoresense.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import scoresense.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByAuthority(String authority);
}
