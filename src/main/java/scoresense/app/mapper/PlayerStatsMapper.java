package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PlayerStatsRequest;
import scoresense.app.dto.PlayerStatsResponse;
import scoresense.app.model.PlayerStats;

public final class PlayerStatsMapper {
    public static PlayerStatsResponse toResponse(PlayerStats stats) {
        if (stats == null) return null;

        return PlayerStatsResponse.builder()
                .playerStatId(stats.getPlayerStatId())
                .goals(stats.getGoals())
                .assists(stats.getAssists())
                .yellowCards(stats.getYellowCards())
                .redCards(stats.getRedCards())
                .minutesPlayed(stats.getMinutesPlayed())
                .playerId(stats.getPlayer() != null ? stats.getPlayer().getPlayerId() : null)
                .matchId(stats.getMatch() != null ? stats.getMatch().getMatchId() : null)
                .build();
    }


    public static List<PlayerStatsResponse> toResponseList(List<PlayerStats> statsList) {
        if (statsList == null) return List.of();
        return statsList.stream()
                .map(PlayerStatsMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static PlayerStats toEntity(PlayerStatsRequest request) {
        if (request == null) return null;

        PlayerStats stats = new PlayerStats();
        stats.setGoals(request.getGoals());
        stats.setAssists(request.getAssists());
        stats.setYellowCards(request.getYellowCards());
        stats.setRedCards(request.getRedCards());
        stats.setMinutesPlayed(request.getMinutesPlayed());

        return stats;
    }


    public static void copyToEntity(PlayerStatsRequest request, PlayerStats entity) {
        if (request == null || entity == null) return;

        entity.setGoals(request.getGoals());
        entity.setAssists(request.getAssists());
        entity.setYellowCards(request.getYellowCards());
        entity.setRedCards(request.getRedCards());
        entity.setMinutesPlayed(request.getMinutesPlayed());

    }
}
