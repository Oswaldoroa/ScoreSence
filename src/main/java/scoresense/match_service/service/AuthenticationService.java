package scoresense.match_service.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import scoresense.match_service.dto.AuthRequest;
import scoresense.match_service.dto.AuthResponse;
import scoresense.match_service.dto.UserRequest;
import scoresense.match_service.mapper.UserMapper;
import scoresense.match_service.model.RoleEntity;
import scoresense.match_service.model.User;
import scoresense.match_service.repository.RoleRepository;
import scoresense.match_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserRequest request) {

        RoleEntity role = roleRepository.findById(request.getRoleId() != null ? request.getRoleId() : 2L)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = UserMapper.toEntity(request, role);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
