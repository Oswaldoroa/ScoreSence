package ScoreSense.App.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trending_topics")
public class TrendingTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    private String socialMedia;
    private String topic;
    private LocalDateTime createdAt = LocalDateTime.now();


    public TrendingTopic() {}

    public TrendingTopic(String socialMedia, String topic) {
        this.socialMedia = socialMedia;
        this.topic = topic;
    }


    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getSocialMedia() { return socialMedia; }
    public void setSocialMedia(String socialMedia) { this.socialMedia = socialMedia; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
