package scoresense.match_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scoresense.match_service.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Override
    Page<Player> findAll(Pageable pageable);

    List<Player> findByNationality(String nationality);

    // Busca por ID numérico (Asegúrate que tu entidad Player tenga 'teamId')
    List<Player> findByPositionAndTeamId(String position, Long teamId);

    List<Player> findByNationalityAndAgeLessThanEqual(String nationality, int maxAge);
}
