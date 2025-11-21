package scoresense.app.mapper;

import scoresense.app.dto.TeamStatsResponse;
import scoresense.app.model.TeamStats;

public final class TeamStatsMapper {

    public static TeamStatsResponse toResponse(TeamStats entity) {
        if (entity == null) return null;

        return TeamStatsResponse.builder()
                .teamStatId(entity.getTeamStatId())
                .possesion(entity.getPossesion())
                .shots(entity.getShots())
                .fouls(entity.getFouls())
                .corners(entity.getCorners())
                .team(entity.getTeam() != null ? TeamMapper.toResponse(entity.getTeam()) : null)
                .match(entity.getMatch() != null ? MatchMapper.toResponse(entity.getMatch()) : null)
                .build();
    }
}