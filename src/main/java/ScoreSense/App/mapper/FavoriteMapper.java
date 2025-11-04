package ScoreSense.App.mapper;
import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.FavoriteRequest;
import ScoreSense.App.dto.FavoriteResponse;
import ScoreSense.App.model.Favorite;

public final class FavoriteMapper {
    public static FavoriteResponse toResponse(Favorite favorite) {
        if (favorite == null) return null;

        return FavoriteResponse.builder()
                .favoriteId(favorite.getFavoriteId())
                .entity_type(favorite.getEntity_type())
                .entity_id(favorite.getEntity_id())
                .userId(favorite.getUser() != null ? favorite.getUser().getUserId() : null)
                .build();
    }


    public static List<FavoriteResponse> toResponseList(List<Favorite> favorites) {
        if (favorites == null) return List.of();
        return favorites.stream()
                .map(FavoriteMapper::toResponse)
                .collect(Collectors.toList());
    }


    public static Favorite toEntity(FavoriteRequest request) {
        if (request == null) return null;

        Favorite favorite = new Favorite();
        favorite.setEntity_type(request.getEntityType());
        favorite.setEntity_id(request.getEntityId());

        return favorite;
    }


    public static void copyToEntity(FavoriteRequest request, Favorite entity) {
        if (request == null || entity == null) return;

        entity.setEntity_type(request.getEntityType());
        entity.setEntity_id(request.getEntityId());
    }
}
