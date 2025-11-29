package scoresense.app.graphql;

import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import lombok.RequiredArgsConstructor; // Usar Lombok para inyección limpia

import java.util.List;

import scoresense.app.dto.TeamResponse;
import scoresense.app.dto.TeamRequest;
import scoresense.app.service.TeamService;
import scoresense.app.repository.TeamRepository; // Importar Repositorio
import scoresense.app.model.Team; // Importar Entidad

@Controller
@RequiredArgsConstructor
public class TeamGraphql {

    private final TeamService service;
    private final TeamRepository repository; // Inyección del repositorio

    // --- QUERIES (Devuelven Entidades para soportar relaciones anidadas) ---

    @QueryMapping
    public List<Team> findAllTeams() {
        return repository.findAll();
    }

    @QueryMapping
    public Team teamById(@Argument Long id) { // Nombre corregido a camelCase
        return repository.findById(id).orElse(null);
    }

    // --- MUTATIONS (Usan Servicio y DTOs) ---

    @MutationMapping
    public TeamResponse createTeam(@Argument TeamRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public TeamResponse updateTeam(@Argument Long id, @Argument TeamRequest req) { // Nombre variable ajustado a 'req'
        return service.update(id, req);
    }

    @MutationMapping
    public Boolean deleteTeam(@Argument Long id) {
        service.delete(id);
        return true;
    }
}