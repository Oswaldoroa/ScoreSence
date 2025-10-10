package ScoreSense.App.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String username;
    private String email;
    private String password_hash;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<FavoriteDTO> favorites;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword_hash() {
        return password_hash;
    }
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public List<FavoriteDTO> getFavorites() {
        return favorites;
    }
    public void setFavorites(List<FavoriteDTO> favorites) {
        this.favorites = favorites;
    }
}
