package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.LeagueRequest;
import ScoreSense.App.dto.LeagueResponse;
import ScoreSense.App.model.League;

public final class LeagueMapper {
    public static LeagueResponse toResponse(League league) {
        if (league == null) return null;

        List<Long> teamIds = null;
        if (league.getTeams() != null) {
            teamIds = league.getTeams().stream()
                    .map(team -> team.getTeamId())
                    .collect(Collectors.toList());
        }

        return LeagueResponse.builder()
                .leagueId(league.getLeagueId())
                .name(league.getName())
                .country(league.getCountry())
                .season(league.getSeason())
                .level(league.getLevel())
                .teamIds(teamIds)
                .build();
    }


    public static List<LeagueResponse> toResponseList(List<League> leagues) {
        if (leagues == null) return List.of();
        return leagues.stream()
                .map(LeagueMapper::toResponse)
                .collect(Collectors.toList());
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
}
