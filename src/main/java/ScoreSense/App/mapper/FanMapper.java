package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.FanDTO;
import ScoreSense.App.model.Fan;

public final class FanMapper {

    public static FanDTO toDTO(Fan fan) {
        if (fan == null) {
            return null;
        }

        FanDTO dto = new FanDTO();

        dto.setId(fan.getFanId());
        dto.setUsername(fan.getUsername());
        dto.setSocialMedia(fan.getSocialMedia());
        dto.setProfilePictureUrl(fan.getProfilePictureUrl());
        dto.setRegisteredAt(fan.getRegisteredAt());

        if (fan.getTeam() != null) {
            dto.setTeamId(fan.getTeam().getTeamId());
        }

        return dto;
    }

    public static Fan toEntity(FanDTO dto) {
        if (dto == null) {
            return null;
        }

        Fan entity = new Fan();

        if (dto.getId() != null) {
            entity.setFanId(dto.getId());
        }

        entity.setUsername(dto.getUsername());
        entity.setSocialMedia(dto.getSocialMedia());
        entity.setProfilePictureUrl(dto.getProfilePictureUrl());
        if (dto.getRegisteredAt() != null) {
            entity.setRegisteredAt(dto.getRegisteredAt());
        }

        return entity;
    }

    public static void copyToEntity(FanDTO dto, Fan entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsername(dto.getUsername());
        entity.setSocialMedia(dto.getSocialMedia());
        entity.setProfilePictureUrl(dto.getProfilePictureUrl());

    }

    public static List<FanDTO> toDTOList(List<Fan> fans) {
        if (fans == null) {
            return List.of();
        }
        return fans.stream()
                .map(FanMapper::toDTO)
                .collect(Collectors.toList());
    }
}
