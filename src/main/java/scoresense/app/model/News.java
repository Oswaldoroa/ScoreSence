package scoresense.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "publish_date")
    private LocalDateTime publishDate = LocalDateTime.now();

    @Column(name = "author")
    private String author;

    @Column(name = "source_url")
    private String source_url;

    @Column(name = "image_url")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
