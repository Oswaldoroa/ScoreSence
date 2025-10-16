package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.FavoriteDTO;
import ScoreSense.App.model.Favorite;

public final class FavoriteMapper {

    public static FavoriteDTO toDTO(Favorite favorite) {
        if (favorite == null) {
            return null;
        }

        FavoriteDTO dto = new FavoriteDTO();

        dto.setEntity_type(favorite.getEntity_type());
        dto.setEntity_id(favorite.getEntity_id());

        if (favorite.getUser() != null) {
            dto.setUserId(favorite.getUser().getUserId());
        }

        return dto;
    }

    public static Favorite toEntity(FavoriteDTO dto) {
        if (dto == null) {
            return null;
        }

        Favorite entity = new Favorite();

        if (dto.getId() != null) {
            entity.setFavoriteId(dto.getId());
        }

        entity.setEntity_type(dto.getEntity_type());
        entity.setEntity_id(dto.getEntity_id());

        return entity;
    }

    public static List<FavoriteDTO> toDTOList(List<Favorite> favorites) {
        if (favorites == null) {
            return List.of();
        }
        return favorites.stream()
                .map(FavoriteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
