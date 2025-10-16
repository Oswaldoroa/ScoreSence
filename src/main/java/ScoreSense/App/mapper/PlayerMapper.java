package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.PlayerDTO;
import ScoreSense.App.model.Player;

public final class PlayerMapper {

    public static PlayerDTO toDTO(Player player) {
        if (player == null) {
            return null;
        }

        PlayerDTO dto = new PlayerDTO();

        dto.setId(player.getPlayerId());
        dto.setName(player.getName());
        dto.setPosition(player.getPosition());
        dto.setAge(player.getAge());
        dto.setNationality(player.getNationality());
        dto.setHeight(player.getHeight());
        dto.setWeight(player.getWeight());

        if (player.getTeam() != null) {
            dto.setTeamId(player.getTeam().getTeamId());
        }

        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        if (dto == null) {
            return null;
        }

        Player entity = new Player();

        if (dto.getId() != null) {
            entity.setPlayerId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        if (dto.getAge() != null) {
            entity.setAge(dto.getAge());
        }
        entity.setNationality(dto.getNationality());
        if (dto.getHeight() != null) {
            entity.setHeight(dto.getHeight());
        }
        if (dto.getWeight() != null) {
            entity.setWeight(dto.getWeight());
        }

        return entity;
    }

    public static void copyToEntity(PlayerDTO dto, Player entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        if (dto.getAge() != null) {
            entity.setAge(dto.getAge());
        }
        entity.setNationality(dto.getNationality());
        if (dto.getHeight() != null) {
            entity.setHeight(dto.getHeight());
        }
        if (dto.getWeight() != null) {
            entity.setWeight(dto.getWeight());
        }
    }

    public static List<PlayerDTO> toDTOList(List<Player> playersList) {
        if (playersList == null) {
            return List.of();
        }
        return playersList.stream()
                .map(PlayerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
