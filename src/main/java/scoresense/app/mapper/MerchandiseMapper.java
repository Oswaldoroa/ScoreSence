package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.dto.TeamResponse;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.model.Merchandise;
import scoresense.app.model.Team;

public final class MerchandiseMapper {

    public static MerchandiseResponse toResponse(Merchandise merchandise) {
        if (merchandise == null) return null;

        Team team = merchandise.getTeam();

        return MerchandiseResponse.builder()
                .merchandiseId(merchandise.getMerchandiseId())
                .name(merchandise.getName())
                .category(merchandise.getCategory())
                .price(merchandise.getPrice())
                .stock(merchandise.getStock())
                .imageUrl(merchandise.getImageUrl())
                .type(merchandise.getType())
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

    public static Merchandise toEntity(MerchandiseRequest request) {
        if (request == null) return null;

        Merchandise merchandise = new Merchandise();
        merchandise.setName(request.getName());
        merchandise.setCategory(request.getCategory());
        merchandise.setPrice(request.getPrice());
        merchandise.setStock(request.getStock());
        merchandise.setImageUrl(request.getImageUrl());
        merchandise.setType(request.getType());
        return merchandise;
    }

    public static void copyToEntity(MerchandiseRequest request, Merchandise entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setCategory(request.getCategory());
        entity.setPrice(request.getPrice());
        entity.setStock(request.getStock());
        entity.setImageUrl(request.getImageUrl());
        entity.setType(request.getType());
    }

    public static List<MerchandiseResponse> toResponseList(List<Merchandise> merchandises) {
        if (merchandises == null) return List.of();

        return merchandises.stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }
}