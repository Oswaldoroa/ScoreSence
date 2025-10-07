package ScoreSense.App.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime publishDate = LocalDateTime.now();

    private String author;
    private String source_url;
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    public News() {}

    public News(String title, String content, String author, String source_url, String image_url, Team team) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.source_url = source_url;
        this.image_url = image_url;
        this.team = team;
    }


    public Long getNewsId() { return newsId; }
    public void setNewsId(Long newsId) { this.newsId = newsId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDateTime publishDate) { this.publishDate = publishDate; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSource_url() { return source_url; }
    public void setSource_url(String source_url) { this.source_url = source_url; }

    public String getImage_url() { return image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
}
