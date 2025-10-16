package ScoreSense.App.service;
import java.util.List;
import ScoreSense.App.model.Fan;

public interface FanService {

    List<Fan> findAll();
    Fan findById(Long id);
    Fan create(Fan fan);
    Fan update(Long id, Fan fan);
    void delete(Long id);
    List<Fan> getFansByName(String name);
    List<Fan> findAll(int page, int pageSize);

}
