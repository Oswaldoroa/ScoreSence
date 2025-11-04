package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.LeagueRequest;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.model.League;

public final class LeagueMapper {

    public static LeagueResponse toResponse(League league) {
        if (league == null) return null;

        return LeagueResponse.builder()
                .leagueId(league.getLeagueId())
                .name(league.getName())
                .country(league.getCountry())
                .season(league.getSeason())
                .level(league.getLevel())
                .build();
    }

    public static League toEntity(LeagueRequest request) {
        if (request == null) return null;

        League league = new League();
        league.setName(request.getName());
        league.setCountry(request.getCountry());
        league.setSeason(request.getSeason());
        league.setLevel(request.getLevel());
        return league;
    }

    public static void copyToEntity(LeagueRequest request, League entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setCountry(request.getCountry());
        entity.setSeason(request.getSeason());
        entity.setLevel(request.getLevel());
    }

    public static List<LeagueResponse> toResponseList(List<League> leagues) {
        if (leagues == null) return List.of();

        return leagues.stream()
                .map(LeagueMapper::toResponse)
                .collect(Collectors.toList());
    }
}