package scoresense.app.mapper;

import scoresense.app.dto.RivalriesRequest;
import scoresense.app.dto.RivalriesResponse;
import scoresense.app.model.Rivalries;
import scoresense.app.model.Team;

public class RivalriesMapper {

    // Convertir Request a Entity
    public static Rivalries toEntity(RivalriesRequest req, Team visitor, Team local) {
        Rivalries rivalry = new Rivalries();
        rivalry.setDescription(req.getDescription());
        rivalry.setTeamVisitor(visitor);
        rivalry.setTeamLocal(local);
        return rivalry;
    }

    // Copiar datos de Request a Entity existente
    public static void copyToEntity(RivalriesRequest req, Rivalries rivalry, Team visitor, Team local) {
        rivalry.setDescription(req.getDescription());
        rivalry.setTeamVisitor(visitor);
        rivalry.setTeamLocal(local);
    }

    // Convertir Entity a Response con objetos completos de Team
    public static RivalriesResponse toResponse(Rivalries rivalry) {
        if (rivalry == null) return null;

        return RivalriesResponse.builder()
                .rivalrieId(rivalry.getRivalrieId())
                .description(rivalry.getDescription())
                .teamVisitor(rivalry.getTeamVisitor() != null ? TeamMapper.toResponse(rivalry.getTeamVisitor()) : null)
                .teamLocal(rivalry.getTeamLocal() != null ? TeamMapper.toResponse(rivalry.getTeamLocal()) : null)
                .build();
    }
}