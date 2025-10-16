package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.PlayerStatsDTO;
import ScoreSense.App.model.PlayerStats;

public final class PlayerStatsMapper {

    public static PlayerStatsDTO toDTO(PlayerStats stats) {
        if (stats == null) {
            return null;
        }

        PlayerStatsDTO dto = new PlayerStatsDTO();

        dto.setId(stats.getPlayerStatId());
        dto.setGoals(stats.getGoals());
        dto.setAssists(stats.getAssists());
        dto.setYellowCards(stats.getYellowCards());
        dto.setRedCards(stats.getRedCards());
        dto.setMinutesPlayed(stats.getMinutesPlayed());

        if (stats.getPlayer() != null) {
            dto.setPlayerId(stats.getPlayer().getPlayerId());
        }

        if (stats.getMatch() != null) {
            dto.setMatchId(stats.getMatch().getMatchId());
        }

        return dto;
    }

    public static PlayerStats toEntity(PlayerStatsDTO dto) {
        if (dto == null) {
            return null;
        }

        PlayerStats entity = new PlayerStats();

        if (dto.getId() != null) {
            entity.setPlayerStatId(dto.getId());
        }

        entity.setGoals(dto.getGoals());
        entity.setAssists(dto.getAssists());
        entity.setYellowCards(dto.getYellowCards());
        entity.setRedCards(dto.getRedCards());
        entity.setMinutesPlayed(dto.getMinutesPlayed());

        return entity;
    }

    public static void copyToEntity(PlayerStatsDTO dto, PlayerStats entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setGoals(dto.getGoals());
        entity.setAssists(dto.getAssists());
        entity.setYellowCards(dto.getYellowCards());
        entity.setRedCards(dto.getRedCards());
        entity.setMinutesPlayed(dto.getMinutesPlayed());
    }

    public static List<PlayerStatsDTO> toDTOList(List<PlayerStats> statsList) {
        if (statsList == null) {
            return List.of();
        }
        return statsList.stream()
                .map(PlayerStatsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
