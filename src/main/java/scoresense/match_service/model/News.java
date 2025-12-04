package scoresense.match_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime publishDate = LocalDateTime.now();
    private String author;

    // CAMBIO: Usamos camelCase para coincidir con el Mapper
    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "image_url")
    private String imageUrl;

    // CAMBIO CRÍTICO: ID en lugar de objeto Team
    @Column(name = "team_id")
    private Long teamId;
}
