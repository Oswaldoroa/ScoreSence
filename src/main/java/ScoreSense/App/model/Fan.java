package ScoreSense.App.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fans")
public class Fan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fan_id")
    private Long fanId;

    @Column(name = "username")
    private String username;

    @Column(name = "social_media")
    private String socialMedia;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


}
