package util;

import dao.RoleDao;
import dao.RoleDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Role;
import model.User;

public class DataInitializer {

    public static void initializeRoles() {
        RoleDao roleDao = new RoleDaoImpl();
        UserDao userDao = new UserDaoImpl();

        Role adminRole = roleDao.findByName("ADMIN");
        Role userRole = roleDao.findByName("USER");

        if (adminRole == null) {
            roleDao.save(new Role("ADMIN"));
        }

        if (userRole == null) {
            roleDao.save(new Role("USER"));
        }

        if (userDao.findByUsername("admin") == null) {
            Role role = userDao.findRoleByName("ADMIN");
            if (role != null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setRole(role);
                userDao.save(admin);
            }
        }
    }
}