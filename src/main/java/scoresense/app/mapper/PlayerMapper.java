package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PlayerRequest;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.dto.TeamResponse;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.model.Player;
import scoresense.app.model.Team;

public final class PlayerMapper {

    public static PlayerResponse toResponse(Player player) {
        if (player == null) return null;

        Team team = player.getTeam();

        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .name(player.getName())
                .position(player.getPosition())
                .age(player.getAge())
                .nationality(player.getNationality())
                .height(player.getHeight())
                .weight(player.getWeight())
                .team(team != null ? TeamResponse.builder()
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
                        .build() : null)
                .build();
    }

    public static Player toEntity(PlayerRequest request) {
        if (request == null) return null;

        Player player = new Player();
        player.setName(request.getName());
        player.setPosition(request.getPosition());
        player.setAge(request.getAge());
        player.setNationality(request.getNationality());
        player.setHeight(request.getHeight());
        player.setWeight(request.getWeight());

        return player;
    }

    public static void copyToEntity(PlayerRequest request, Player entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setPosition(request.getPosition());
        entity.setAge(request.getAge());
        entity.setNationality(request.getNationality());
        entity.setHeight(request.getHeight());
        entity.setWeight(request.getWeight());
    }

    public static List<PlayerResponse> toResponseList(List<Player> players) {
        if (players == null) return List.of();

        return players.stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }
}