package scoresense.match_service.mapper;

import scoresense.match_service.dto.MatchRequest;
import scoresense.match_service.dto.MatchResponse;
import scoresense.match_service.model.Match;

public final class MatchMapper {

    public static Match toEntity(MatchRequest req) {
        if (req == null) {
            return null;
        }

        Match match = new Match();
        match.setMatchDate(req.getMatchDate());
        match.setHomeScore(req.getHomeScore());
        match.setAwayScore(req.getAwayScore());

        // Asignación directa de IDs
        match.setHomeTeamId(req.getHomeTeamId());
        match.setAwayTeamId(req.getAwayTeamId());
        match.setRefereeId(req.getRefereeId());

        return match;
    }

    public static MatchResponse toResponse(Match match) {
        if (match == null) {
            return null;
        }

        return MatchResponse.builder()
                .matchId(match.getMatchId())
                .matchDate(match.getMatchDate())
                .homeScore(match.getHomeScore())
                .awayScore(match.getAwayScore())
                .homeTeamId(match.getHomeTeamId()) // Obtener ID directo
                .awayTeamId(match.getAwayTeamId()) // Obtener ID directo
                .refereeId(match.getRefereeId())
                .build();
    }
}
