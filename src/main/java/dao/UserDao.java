package dao;

import java.util.List;
import model.User;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
}