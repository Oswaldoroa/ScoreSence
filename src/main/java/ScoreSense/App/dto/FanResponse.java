package scoresense.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FanResponse {
    @JsonProperty("fan_id")
    Long fanId;
    @JsonProperty("username")
    String username;
    @JsonProperty("social_media")
    String socialMedia;
    @JsonProperty("profile_picture_url")
    String profilePictureUrl;
    @JsonProperty("registered_at")
    LocalDateTime registeredAt;
    @JsonProperty("team_id")
    Long teamId;
}
