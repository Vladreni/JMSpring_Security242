package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();
    void del(User user);
    void update(User user);
    User getUserById(Long id);
    User getUserByName(String name);


}
