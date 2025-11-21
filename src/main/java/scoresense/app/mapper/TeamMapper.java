package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.LeagueResponse;
import scoresense.app.dto.TeamRequest;
import scoresense.app.dto.TeamResponse;
import scoresense.app.model.Team;

public final class TeamMapper {

    public static TeamResponse toResponse(Team team) {
        if (team == null) return null;

        return TeamResponse.builder()
                .teamId(team.getTeamId())
                .name(team.getName())
                .country(team.getCountry())
                .foundedYear(team.getFoundedYear())
                .stadium(team.getStadium())
                .logoUrl(team.getLogoUrl())
                .league(team.getLeague() != null ? LeagueResponse.builder()
                        .leagueId(team.getLeague().getLeagueId())
                        .name(team.getLeague().getName())
                        .country(team.getLeague().getCountry())
                        .season(team.getLeague().getSeason())
                        .level(team.getLeague().getLevel())
                        .build() : null)
                .build();
    }


    public static Team toEntity(TeamRequest request) {
        if (request == null) return null;

        Team team = new Team();
        team.setName(request.getName());
        team.setCountry(request.getCountry());
        team.setFoundedYear(request.getFoundedYear());
        team.setStadium(request.getStadium());
        team.setLogoUrl(request.getLogoUrl());
        return team;
    }

    public static void copyToEntity(TeamRequest request, Team entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setCountry(request.getCountry());
        entity.setFoundedYear(request.getFoundedYear());
        entity.setStadium(request.getStadium());
        entity.setLogoUrl(request.getLogoUrl());
    }

    public static List<TeamResponse> toResponseList(List<Team> teams) {
        if (teams == null) return List.of();

        return teams.stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }
}