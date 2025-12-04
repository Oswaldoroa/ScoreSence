package scoresense.match_service.mapper;

import scoresense.match_service.dto.PlayerRequest;
import scoresense.match_service.dto.PlayerResponse;
import scoresense.match_service.model.Player;

public final class PlayerMapper {

    public static Player toEntity(PlayerRequest req) {
        if (req == null) {
            return null;
        }
        Player player = new Player();
        player.setName(req.getName());
        player.setPosition(req.getPosition());
        player.setAge(req.getAge());
        player.setNationality(req.getNationality());
        player.setHeight(req.getHeight());
        player.setWeight(req.getWeight());
        player.setTeamId(req.getTeamId()); // Asignación directa
        return player;
    }

    public static PlayerResponse toResponse(Player player) {
        if (player == null) {
            return null;
        }

        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .name(player.getName())
                .position(player.getPosition())
                .age(player.getAge())
                .nationality(player.getNationality())
                .height(player.getHeight())
                .weight(player.getWeight())
                .teamId(player.getTeamId()) // Lectura directa
                .build();
    }

    public static void copyToEntity(PlayerRequest req, Player entity) {
        if (req == null || entity == null) {
            return;
        }
        entity.setName(req.getName());
        entity.setPosition(req.getPosition());
        entity.setAge(req.getAge());
        entity.setNationality(req.getNationality());
        entity.setHeight(req.getHeight());
        entity.setWeight(req.getWeight());
        entity.setTeamId(req.getTeamId()); // Actualización directa
    }
}
