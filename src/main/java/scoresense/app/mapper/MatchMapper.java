package scoresense.app.mapper;

import scoresense.app.dto.MatchRequest;
import scoresense.app.dto.MatchResponse;
import scoresense.app.model.Match;
import scoresense.app.model.Team;

public class MatchMapper {

    public static Match toEntity(MatchRequest req, Team homeTeam, Team awayTeam) {
        Match match = new Match();
        match.setMatchDate(req.getMatchDate());
        match.setHomeScore(req.getHomeScore());
        match.setAwayScore(req.getAwayScore());
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        return match;
    }

    public static void copyToEntity(MatchRequest req, Match match, Team homeTeam, Team awayTeam) {
        match.setMatchDate(req.getMatchDate());
        match.setHomeScore(req.getHomeScore());
        match.setAwayScore(req.getAwayScore());
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
    }

    public static MatchResponse toResponse(Match match) {
    if (match == null) return null;

    return MatchResponse.builder()
        .matchId(match.getMatchId())
        .matchDate(match.getMatchDate())
        .homeScore(match.getHomeScore())
        .awayScore(match.getAwayScore())
        .homeTeam(match.getHomeTeam() != null ? TeamMapper.toResponse(match.getHomeTeam()) : null)
        .awayTeam(match.getAwayTeam() != null ? TeamMapper.toResponse(match.getAwayTeam()) : null)
        .build();
    }
}