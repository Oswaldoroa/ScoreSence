package scoresense.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import scoresense.app.dto.UserLoginRequest;
import scoresense.app.dto.UserLoginResponse;
import scoresense.app.dto.UserRequest;
import scoresense.app.dto.UserResponse;
import scoresense.app.mapper.UserLoginMapper;
import scoresense.app.service.JwtService;
import scoresense.app.model.User;
import scoresense.app.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;
    private final JwtService jwtService;
    // Ya no necesitamos PasswordEncoder aquí

    @PostMapping
    public UserResponse create(@RequestBody UserRequest user) {
        // SIMPLIFICADO: Solo llamamos al servicio. Él se encarga de la seguridad.
        return service.create(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody UserLoginRequest loginRequest) {
        // Autenticamos
        User authenticatedUser = service.authenticate(loginRequest);
        
        // Preparamos la respuesta
        UserLoginResponse userLoginResponse = UserLoginMapper.toResponse(authenticatedUser);

        // Generamos el Token JWT
        String jwtToken = jwtService.generateToken(authenticatedUser);
        userLoginResponse.setToken(jwtToken);
        userLoginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        return ResponseEntity.ok(userLoginResponse);
    }
}