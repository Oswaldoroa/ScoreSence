package scoresense.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import scoresense.app.dto.UserRequest;
import scoresense.app.dto.UserResponse;
import scoresense.app.mapper.UserMapper;
import scoresense.app.model.RoleEntity;
import scoresense.app.model.User;
import scoresense.app.repository.RoleRepository;
import scoresense.app.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User API Endpoints")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Return user by using ID")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user with the provided information and assigned role")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest req) {
        RoleEntity role = roleRepository.findById(req.getRoleId()).orElse(null);
        User user = UserMapper.toEntity(req, role);
        User saved = userRepository.save(user);
        UserResponse created = UserMapper.toResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user information by ID")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest req) {
        RoleEntity role = roleRepository.findById(req.getRoleId()).orElse(null);
        return userRepository.findById(id).map(user -> {
            UserMapper.copyToEntity(req, user, role);
            User updated = userRepository.save(user);
            return ResponseEntity.ok(UserMapper.toResponse(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}