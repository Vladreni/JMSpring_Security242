package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {

        User userFromDB = userDao.getUserByName(user.getName());
        if (userFromDB != null) {
            //System.out.println("from return");
            return ;
        }

        userDao.add(get(user));
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void del(User user) {
        userDao.del(user);
    }

    @Transactional
    @Override
    public void update(User user) {

        userDao.update(get(user));
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }


    private User get(User user) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_USER"));

        if (user.getAdmin() != null && user.getAdmin().equals("ROLE_ADMIN")) {
            roleSet.add(new Role(2L, "ROLE_ADMIN"));

        }
        user.setRoles(roleSet);
        return user;
    }



    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService,
    // с единственным методом:

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + s);
        }
        return user;
    }
}
