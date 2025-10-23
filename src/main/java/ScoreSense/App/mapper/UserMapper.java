package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.UserRequest;
import scoresense.app.dto.UserResponse;
import scoresense.app.model.User;

public final class UserMapper {
    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setFavoriteIds(user.getFavorites() != null
                ? user.getFavorites().stream().map(f -> f.getFavoriteId()).toList()
                : List.of());

        return response;
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        if (users == null) return List.of();
        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static User toEntity(UserRequest request) {
        if (request == null) return null;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword_hash(request.getPassword()); // mapeamos password directamente al hash

        return user;
    }

    public static void copyToEntity(UserRequest request, User entity) {
        if (request == null || entity == null) return;

        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword_hash(request.getPassword());
    }
}
