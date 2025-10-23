package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.TeamStatsRequest;
import scoresense.app.dto.TeamStatsResponse;
import scoresense.app.model.TeamStats;

public final class TeamStatsMapper {
    public static TeamStatsResponse toResponse(TeamStats stats) {
        if (stats == null) return null;

        TeamStatsResponse response = new TeamStatsResponse();
        response.setTeamStatId(stats.getTeamStatId());
        response.setPossession(stats.getPossesion()); // mapeo del modelo al DTO
        response.setShots(stats.getShots());
        response.setFouls(stats.getFouls());
        response.setCorners(stats.getCorners());
        response.setTeamId(stats.getTeam() != null ? stats.getTeam().getTeamId() : null);
        response.setMatchId(stats.getMatch() != null ? stats.getMatch().getMatchId() : null);

        return response;
    }

    public static List<TeamStatsResponse> toResponseList(List<TeamStats> statsList) {
        if (statsList == null) return List.of();
        return statsList.stream()
                .map(TeamStatsMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static TeamStats toEntity(TeamStatsRequest request) {
        if (request == null) return null;

        TeamStats stats = new TeamStats();
        stats.setPossesion(request.getPossesion());
        stats.setShots(request.getShots());
        stats.setFouls(request.getFouls());
        stats.setCorners(request.getCorners());

        return stats;
    }

    public static void copyToEntity(TeamStatsRequest request, TeamStats entity) {
        if (request == null || entity == null) return;

        entity.setPossesion(request.getPossesion());
        entity.setShots(request.getShots());
        entity.setFouls(request.getFouls());
        entity.setCorners(request.getCorners());
    }
}
