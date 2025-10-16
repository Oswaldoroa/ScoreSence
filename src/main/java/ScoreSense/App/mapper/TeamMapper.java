package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.TeamRequest;
import ScoreSense.App.dto.TeamResponse;
import ScoreSense.App.model.Team;

public final class TeamMapper {
    public static TeamResponse toResponse(Team team) {
        if (team == null) return null;

        return TeamResponse.builder()
                .teamId(team.getTeamId())
                .name(team.getName())
                .country(team.getCountry())
                .foundedYear(team.getFounded_year())
                .stadium(team.getStadium())
                .logoUrl(team.getLogo_url())
                .leagueId(team.getLeague() != null ? team.getLeague().getLeagueId() : null)
                .coachId(team.getCoach() != null ? team.getCoach().getCoachId() : null)
                .playerIds(team.getPlayers() != null ?
                        team.getPlayers().stream().map(p -> p.getPlayerId()).toList() : List.of())
                .sentimentIds(team.getSentiments() != null ?
                        team.getSentiments().stream().map(s -> s.getSentimentId()).toList() : List.of())
                .build();
    }


    public static List<TeamResponse> toResponseList(List<Team> teams) {
        if (teams == null) return List.of();
        return teams.stream()
                .map(TeamMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Team toEntity(TeamRequest request) {
        if (request == null) return null;

        Team team = new Team();
        team.setName(request.getName());
        team.setCountry(request.getCountry());
        team.setFounded_year(request.getFoundedYear());
        team.setStadium(request.getStadium());
        team.setLogo_url(request.getLogoUrl());

        return team;
    }


    public static void copyToEntity(TeamRequest request, Team entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setCountry(request.getCountry());
        entity.setFounded_year(request.getFoundedYear());
        entity.setStadium(request.getStadium());
        entity.setLogo_url(request.getLogoUrl());

    }
}
