package ScoreSense.App.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sentiments")
public class Sentiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sentimentId;

    private String source; // Twitter, Facebook, etc.
    private String sentiment; // positive, neutral, negative
    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    public Sentiment() {}

    public Sentiment(String source, String sentiment, String comment, Team team) {
        this.source = source;
        this.sentiment = sentiment;
        this.comment = comment;
        this.team = team;
    }


    public Long getSentimentId() { return sentimentId; }
    public void setSentimentId(Long sentimentId) { this.sentimentId = sentimentId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
