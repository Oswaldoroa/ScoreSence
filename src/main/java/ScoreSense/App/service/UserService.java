package scoresense.app.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import scoresense.app.dto.UserLoginRequest;
import scoresense.app.dto.UserRequest;
import scoresense.app.dto.UserResponse;
import scoresense.app.mapper.UserMapper;
import scoresense.app.model.Role;
import scoresense.app.model.User;
import scoresense.app.repository.RoleRepository;
import scoresense.app.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder; 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        // 1. CORRECCIÓN: Usar "ROLE_USER" por defecto para coincidir con tu BD
        String roleName = (request.getRole() == null || request.getRole().isEmpty()) 
                          ? "ROLE_USER" 
                          : request.getRole();

        // 2. Buscar el rol (ahora sí lo encontrará)
        Role userRole = roleRepository.findByAuthority(roleName)
                .orElseThrow(() -> new NoSuchElementException("Role not found: " + roleName));

        // 3. Crear usuario y encriptar contraseña AQUÍ
        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword())); 
        
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        user.setAuthorities(authorities);

        // 4. Guardar
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    public User authenticate(UserLoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}