package scoresense.app.mapper;

import java.util.Set;
import java.util.Iterator;

import scoresense.app.dto.UserRequest;
import scoresense.app.dto.UserResponse;
import scoresense.app.model.User;
import scoresense.app.model.Role;

public final class UserMapper {
   public static UserResponse toResponse(User user) {
        if (user == null) return null;

        String role = user.getAuthorities().stream()
            .map(Role::getAuthority)
            .findFirst()
            .orElse("ROLE_USER"); 

        return UserResponse.builder()
                .email(user.getEmail())
                .role(role)
                .build();
    }

    public static User toEntity(UserRequest dto) {
        if (dto == null) return null;

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UserRequest dto, User entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}