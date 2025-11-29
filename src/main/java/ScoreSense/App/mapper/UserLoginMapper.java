package scoresense.app.mapper;

import scoresense.app.dto.UserLoginResponse;
import scoresense.app.dto.UserLoginRequest;
import scoresense.app.model.User;
import scoresense.app.model.Role;

public final class UserLoginMapper {
    public static UserLoginResponse toResponse(User user) {
        if (user == null)
            return null;

        // Lógica corregida para extraer el rol de forma segura
        String role = user.getAuthorities().stream()
            .map(Role::getAuthority)
            .findFirst()
            .orElse("ROLE_USER");

        return UserLoginResponse.builder()
                .email(user.getEmail())
                .role(role) // Asigna el rol correcto o el por defecto
                .build();
    }

    public static User toEntity(UserLoginRequest dto) {
        if (dto == null)
            return null;

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UserLoginRequest dto, User entity) {
        if (dto == null || entity == null)
            return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}