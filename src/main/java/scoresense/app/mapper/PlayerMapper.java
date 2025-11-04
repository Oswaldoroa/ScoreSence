package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PlayerRequest;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.model.Player;

public final class PlayerMapper {
    public static PlayerResponse toResponse(Player player) {
        if (player == null) return null;

        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .name(player.getName())
                .position(player.getPosition())
                .age(player.getAge())
                .nationality(player.getNationality())
                .height(player.getHeight())
                .weight(player.getWeight())
                .teamId(player.getTeam() != null ? player.getTeam().getTeamId() : null)
                .build();
    }


    public static List<PlayerResponse> toResponseList(List<Player> players) {
        if (players == null) return List.of();
        return players.stream()
                .map(PlayerMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Player toEntity(PlayerRequest request) {
        if (request == null) return null;

        Player player = new Player();
        player.setPosition(request.getPosition());
        player.setAge(request.getAge());
        player.setNationality(request.getNationality());
        player.setHeight(request.getHeight());
        player.setWeight(request.getWeight());
        return player;
    }


    public static void copyToEntity(PlayerRequest request, Player entity) {
        if (request == null || entity == null) return;

        entity.setPosition(request.getPosition());
        entity.setAge(request.getAge());
        entity.setNationality(request.getNationality());
        entity.setHeight(request.getHeight());
        entity.setWeight(request.getWeight());

    }
}
