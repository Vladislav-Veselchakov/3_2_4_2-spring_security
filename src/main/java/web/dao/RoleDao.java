package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    void add(Role role);
    void update(Role role);
    List<Role> getRoles();
    public void deleteRole(Long id);
    Role getRoleById(Long id);
}
