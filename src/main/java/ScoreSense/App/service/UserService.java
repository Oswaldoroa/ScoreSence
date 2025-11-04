package ScoreSense.App.service;

import java.util.List;
import ScoreSense.App.model.User;
public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
}
