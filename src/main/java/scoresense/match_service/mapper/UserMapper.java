package scoresense.match_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.match_service.dto.UserRequest;
import scoresense.match_service.dto.UserResponse;
import scoresense.match_service.model.RoleEntity;
import scoresense.match_service.model.User;

public final class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .roleId(user.getRole() != null ? user.getRole().getRoleId() : null)
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        if (users == null) {
            return List.of();
        }
        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static User toEntity(UserRequest request, RoleEntity role) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(role);
        return user;
    }

    public static void copyToEntity(UserRequest request, User entity, RoleEntity role) {
        if (request == null || entity == null) {
            return;
        }

        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        if (role != null) {
            entity.setRole(role);
        }
    }
}
