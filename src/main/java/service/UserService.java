package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    boolean usernameExists(String username);
    boolean roleExists(String roleName);
    boolean registerUser(String username, String password, String roleName);
}