package web.service;

import web.model.Role;
import web.model.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserService {
    void add(User user);
    void update(User user);
    List<User> getUsers();
    public void deleteUser(Long id);
    User getUserById(Long id);
    Set<Role> getRoles(Long id);
    void setModified(User user, Date modified);
    void setRoleByName(User user, String roleName);
}
