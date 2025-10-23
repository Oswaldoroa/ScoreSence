package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.MatchRequest;
import scoresense.app.dto.MatchResponse;
import scoresense.app.model.Match;

public final class MatchMapper {
    public static MatchResponse toResponse(Match match) {
        if (match == null) return null;

        return MatchResponse.builder()
                .matchId(match.getMatchId())
                .matchDate(match.getMatchDate())
                .homeScore(match.getHomeScore())
                .awayScore(match.getAwayScore())
                .homeTeamId(match.getHomeTeam() != null ? match.getHomeTeam().getTeamId() : null)
                .awayTeamId(match.getAwayTeam() != null ? match.getAwayTeam().getTeamId() : null)
                .build();
    }


    public static List<MatchResponse> toResponseList(List<Match> matches) {
        if (matches == null) return List.of();
        return matches.stream()
                .map(MatchMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Match toEntity(MatchRequest request) {
        if (request == null) return null;

        Match match = new Match();
        match.setMatchDate(request.getMatchDate());
        match.setHomeScore(request.getHomeScore());
        match.setAwayScore(request.getAwayScore());
        return match;
    }


    public static void copyToEntity(MatchRequest request, Match entity) {
        if (request == null || entity == null) return;

        entity.setMatchDate(request.getMatchDate());
        entity.setHomeScore(request.getHomeScore());
        entity.setAwayScore(request.getAwayScore());

    }
}
