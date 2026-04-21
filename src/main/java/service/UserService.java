package service;

import java.util.List;
import model.User;

public interface UserService {
    boolean registerUser(String username, String password, String roleName);
    User login(String username, String password);
    User findByUsername(String username);
    List<User> findAllUsers();
}