package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.RivalriesDTO;
import ScoreSense.App.model.Rivalries;

public final class RivalriesMapper {

    public static RivalriesDTO toDTO(Rivalries rivalry) {
        if (rivalry == null) {
            return null;
        }

        RivalriesDTO dto = new RivalriesDTO();

        dto.setId(rivalry.getRivalrieId());

        dto.setDescription(rivalry.getDescription());

        if (rivalry.getTeamLocalId() != null) {
            dto.setTeam1Id(rivalry.getTeamLocalId().getTeamId());
        }

        if (rivalry.getTeamVisitorId() != null) {
            dto.setTeam2Id(rivalry.getTeamVisitorId().getTeamId());
        }
        return dto;
    }

    public static Rivalries toEntity(RivalriesDTO dto) {
        if (dto == null) {
            return null;
        }

        Rivalries entity = new Rivalries();

        if (dto.getId() != null) {
            entity.setRivalrieId(dto.getId());
        }

        entity.setDescription(dto.getDescription());

        return entity;
    }

    public static void copyToEntity(RivalriesDTO dto, Rivalries entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setDescription(dto.getDescription());
    }

    public static List<RivalriesDTO> toDTOList(List<Rivalries> rivalriesList) {
        if (rivalriesList == null) {
            return List.of();
        }
        return rivalriesList.stream()
                .map(RivalriesMapper::toDTO)
                .collect(Collectors.toList());
    }
}
