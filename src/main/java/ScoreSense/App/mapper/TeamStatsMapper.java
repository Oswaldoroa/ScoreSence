package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.TeamStatsDTO;
import ScoreSense.App.model.TeamStats;

public final class TeamStatsMapper {

    public static TeamStatsDTO toDTO(TeamStats stats) {
        if (stats == null) {
            return null;
        }

        TeamStatsDTO dto = new TeamStatsDTO();

        dto.setId(stats.getTeamStatId());
        dto.setPossesion(stats.getPossesion());
        dto.setShots(stats.getShots());
        dto.setFouls(stats.getFouls());
        dto.setCorners(stats.getCorners());

        if (stats.getTeam() != null) {
            dto.setTeamId(stats.getTeam().getTeamId());
        }

        if (stats.getMatch() != null) {
            dto.setMatchId(stats.getMatch().getMatchId());
        }

        return dto;
    }

    public static TeamStats toEntity(TeamStatsDTO dto) {
        if (dto == null) {
            return null;
        }

        TeamStats entity = new TeamStats();

        if (dto.getId() != null) {
            entity.setTeamStatId(dto.getId());
        }

        entity.setPossesion(dto.getPossesion());
        entity.setShots(dto.getShots());
        entity.setFouls(dto.getFouls());
        entity.setCorners(dto.getCorners());

        return entity;
    }

    public static void copyToEntity(TeamStatsDTO dto, TeamStats entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setPossesion(dto.getPossesion());
        entity.setShots(dto.getShots());
        entity.setFouls(dto.getFouls());
        entity.setCorners(dto.getCorners());
    }

    public static List<TeamStatsDTO> toDTOList(List<TeamStats> statsList) {
        if (statsList == null) {
            return List.of();
        }
        return statsList.stream()
                .map(TeamStatsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
