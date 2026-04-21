package service;

import java.util.List;

import dao.RoleDao;
import dao.RoleDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Role;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public boolean registerUser(String username, String password, String roleName) {
        User existing = userDao.findByUsername(username);
        if (existing != null) {
            return false;
        }

        Role role = roleDao.findByName(roleName);
        if (role == null) {
            return false;
        }

        User user = new User(username, password, role);
        userDao.save(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}