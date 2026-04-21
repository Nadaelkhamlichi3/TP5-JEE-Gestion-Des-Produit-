package util;

import dao.RoleDao;
import dao.RoleDaoImpl;
import model.Role;

public class DataInitializer {

    public static void initializeRoles() {
        RoleDao roleDao = new RoleDaoImpl();

        if (roleDao.findByName("ADMIN") == null) {
            roleDao.save(new Role("ADMIN"));
        }

        if (roleDao.findByName("USER") == null) {
            roleDao.save(new Role("USER"));
        }
    }
}