package ScoreSense.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String entity_type;
    private Long entity_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Favorite() {}

    public Favorite(String entity_type, Long entity_id, User user) {
        this.entity_type = entity_type;
        this.entity_id = entity_id;
        this.user = user;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEntity_type() { return entity_type; }
    public void setEntity_type(String entity_type) { this.entity_type = entity_type; }

    public Long getEntity_id() { return entity_id; }
    public void setEntity_id(Long entity_id) { this.entity_id = entity_id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
