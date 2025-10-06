package ScoreSense.App.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fans")
public class Fan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fanId;

    private String username;
    private String socialMedia;
    private String profilePictureUrl;
    private LocalDateTime registeredAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Fan() {}

    public Fan(String username, String socialMedia, String profilePictureUrl, Team team) {
        this.username = username;
        this.socialMedia = socialMedia;
        this.profilePictureUrl = profilePictureUrl;
        this.team = team;
    }


    public Long getFanId() { return fanId; }
    public void setFanId(Long fanId) { this.fanId = fanId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSocialMedia() { return socialMedia; }
    public void setSocialMedia(String socialMedia) { this.socialMedia = socialMedia; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
