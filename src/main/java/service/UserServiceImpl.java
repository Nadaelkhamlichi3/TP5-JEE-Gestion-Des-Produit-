package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.Role;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean usernameExists(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    public boolean roleExists(String roleName) {
        Role role = userDao.findRoleByName(roleName);
        return role != null;
    }

    @Override
    public boolean registerUser(String username, String password, String roleName) {
        Role role = userDao.findRoleByName(roleName);

        if (role == null) {
            System.out.println("ROLE INTROUVABLE : " + roleName);
            return false;
        }

        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(password.trim());
        user.setRole(role);

        userDao.save(user);
        return true;
    }
}