package scoresense.app.controller;

import scoresense.app.config.JwtUtil;
import scoresense.app.config.CustomUserDetailsService;
import scoresense.app.model.RoleEntity;
import scoresense.app.model.User;
import scoresense.app.repository.RoleRepository;
import scoresense.app.repository.UserRepository;
import scoresense.app.dto.AuthRequest;
import scoresense.app.dto.AuthResponse;
import scoresense.app.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User u = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String roleName = u.getRole() != null ? u.getRole().getName() : "USER";

        String token = jwtUtil.generateToken(userDetails.getUsername(), roleName);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User u = new User();
        u.setUsername(request.getUsername());
        u.setEmail(request.getEmail());
        u.setPassword_hash(passwordEncoder.encode(request.getPassword()));

        String wantedRole = request.getRole() != null ? request.getRole().toUpperCase() : "USER";
        Optional<RoleEntity> roleOpt = roleRepository.findByName(wantedRole);
        RoleEntity role = roleOpt.orElseGet(() -> {
            RoleEntity r = new RoleEntity();
            r.setName(wantedRole);
            r.setDescription("Auto-created");
            return roleRepository.save(r);
        });
        u.setRole(role);

        userRepository.save(u);

        String token = jwtUtil.generateToken(u.getUsername(), role.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}