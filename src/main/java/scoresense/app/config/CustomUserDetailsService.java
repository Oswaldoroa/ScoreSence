package scoresense.app.config;

import scoresense.app.model.User;
import scoresense.app.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String roleName = u.getRole() != null ? u.getRole().getName() : "USER";

        return new org.springframework.security.core.userdetails.User(
            u.getUsername(),
            u.getPassword_hash(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + u.getRole().getName()))
        );

    }
}