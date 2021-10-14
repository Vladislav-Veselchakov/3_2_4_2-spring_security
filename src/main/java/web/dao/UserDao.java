package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> getUsers();
    User getUserById(Long id);
    User getUserByName(String name);
    void deleteUser(Long id);
    void update(User user);
}
