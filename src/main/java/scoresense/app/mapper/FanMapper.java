package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.FanRequest;
import scoresense.app.dto.FanResponse;
import scoresense.app.model.Fan;

public final class FanMapper {
    public static FanResponse toResponse(Fan fan) {
        if (fan == null) return null;

        return FanResponse.builder()
                .fanId(fan.getFanId())
                .username(fan.getUsername())
                .socialMedia(fan.getSocialMedia())
                .profilePictureUrl(fan.getProfilePictureUrl())
                .registeredAt(fan.getRegisteredAt())
                .teamId(fan.getTeam() != null ? fan.getTeam().getTeamId() : null)
                .build();
    }


    public static List<FanResponse> toResponseList(List<Fan> fans) {
        if (fans == null) return List.of();
        return fans.stream()
                .map(FanMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Fan toEntity(FanRequest request) {
        if (request == null) return null;

        Fan fan = new Fan();
        fan.setUsername(request.getUsername());
        fan.setSocialMedia(request.getSocialMedia());
        fan.setProfilePictureUrl(request.getProfilePictureUrl());

        return fan;
    }


    public static void copyToEntity(FanRequest request, Fan entity) {
        if (request == null || entity == null) return;

        entity.setUsername(request.getUsername());
        entity.setSocialMedia(request.getSocialMedia());
        entity.setProfilePictureUrl(request.getProfilePictureUrl());
    }
}
