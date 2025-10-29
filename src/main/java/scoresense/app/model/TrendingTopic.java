package scoresense.app.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trending_topics")
public class TrendingTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "social_media")
    private String socialMedia;

    @Column(name = "topic")
    private String topic;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
