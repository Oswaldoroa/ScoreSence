package scoresense.app.controller;

import scoresense.app.model.User;
import scoresense.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @Operation(summary = "Get users", description = "Get all users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a user", description = "Get a user by ID")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @PostMapping
    @Operation(summary = "Create a user", description = "Create a user by ID")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Create a user", description = "Update a user by ID")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword_hash(userDetails.getPassword_hash());
            return userRepository.save(user);
        }).orElse(null);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by ID")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }


}
