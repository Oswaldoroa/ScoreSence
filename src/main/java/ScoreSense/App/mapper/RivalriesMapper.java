package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.RivalriesRequest;
import scoresense.app.dto.RivalriesResponse;
import scoresense.app.model.Rivalries;

public final class RivalriesMapper {

    public static RivalriesResponse toResponse(Rivalries rivalries) {
        if (rivalries == null) {
            return null;
        }

        return RivalriesResponse.builder()
                .rivalrieId(rivalries.getRivalrieId())
                .description(rivalries.getDescription())
                .teamVisitorId(rivalries.getTeamVisitor() != null ? rivalries.getTeamVisitor().getTeamId() : null)
                .teamLocalId(rivalries.getTeamLocal() != null ? rivalries.getTeamLocal().getTeamId() : null)
                .build();
    }

    public static List<RivalriesResponse> toResponseList(List<Rivalries> rivalriesList) {
        if (rivalriesList == null) {
            return List.of();
        }
        return rivalriesList.stream()
                .map(RivalriesMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static Rivalries toEntity(RivalriesRequest request) {
        if (request == null) {
            return null;
        }

        Rivalries rivalries = new Rivalries();
        rivalries.setDescription(request.getDescription());
        return rivalries;
    }

    public static void copyToEntity(RivalriesRequest request, Rivalries entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setDescription(request.getDescription());

    }
}
