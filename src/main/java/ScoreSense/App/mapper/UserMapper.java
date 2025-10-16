package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.UserDTO;
import ScoreSense.App.model.Favorite;
import ScoreSense.App.model.User;

public final class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.setId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword_hash(user.getPassword_hash());
        dto.setCreatedAt(user.getCreatedAt());

        List<Favorite> favorites = user.getFavorites();
        if (favorites != null && !favorites.isEmpty()) {
            dto.setFavorites(FavoriteMapper.toDTOList(favorites));
        } else {
            dto.setFavorites(List.of());
        }

        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();

        if (dto.getId() != null) {
            entity.setUserId(dto.getId());
        }

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword_hash(dto.getPassword_hash());

        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }

        return entity;
    }

    public static void copyToEntity(UserDTO dto, User entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        if (dto.getPassword_hash() != null) {
            entity.setPassword_hash(dto.getPassword_hash());
        }
    }

    public static List<UserDTO> toDTOList(List<User> userList) {
        if (userList == null) {
            return List.of();
        }
        return userList.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
