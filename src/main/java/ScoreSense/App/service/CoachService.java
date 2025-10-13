package ScoreSense.App.service;
import java.util.List;
import ScoreSense.App.model.Coach;
public interface CoachService {
    List<Coach> findAll();
    Coach findById(Long id);
    Coach create(Coach coach);
    Coach update(Long id, Coach coach);
    void delete(Long id);
    List<Coach> getCoachesByName(String name);
    List<Coach> findAll(int page, int pageSize);
}
