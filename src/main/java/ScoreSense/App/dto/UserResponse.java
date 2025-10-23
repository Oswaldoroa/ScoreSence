package scoresense.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("username")
    String username;
    @JsonProperty("email")
    String email;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
    @JsonProperty("favorite_ids")
    List<Long> favoriteIds;
}
