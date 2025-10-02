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
    private LocalDateTime registeredAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Constructors
    public Fan() {}

    public Fan(String username, String socialMedia, Team team) {
        this.username = username;
        this.socialMedia = socialMedia;
        this.team = team;
    }


    public Long getFanId() { return fanId; }
    public void setFanId(Long fanId) { this.fanId = fanId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSocialMedia() { return socialMedia; }
    public void setSocialMedia(String socialMedia) { this.socialMedia = socialMedia; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
