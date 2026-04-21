package dao;

import java.util.List;
import model.Role;

public interface RoleDao {
    void save(Role role);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
}