package web.dao;

import org.hibernate.query.Query;
import web.model.Role;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


//    private final Map<String, User> userMap =
//            Collections.singletonMap(
//                    "tst",
//                    new User(
//                            "tst",
//                            "tst",
//                            Collections.singleton(new Role(1L, "ROLE_ADMIN"))));
//    // name - уникальное значение, выступает в качестве ключа Map

//    @Override
//    public User getUserByName(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//
//        return userMap.get(name);
//    }

    @Override
    public void add(User user) {

        entityManager.persist(user);

    }

    @Override
    public User getUserById(Long id) {

        User user = entityManager.find(User.class, id);
        //entityManager.detach(user);
        return user;
    }

    @Override
    public User getUserByName(String name) {
//        TypedQuery q = entityManager.createQuery(
//                "SELECT u FROM User u WHERE u.name = :name", User.class);

        Query q = (Query) entityManager.createQuery(
                "select u from User u where u.name = :name");
        q.setParameter("name", name);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        //userRepository.findByUsername(name);

    }

    @Override
    public Role getDBRole(String role) {
        Query q = (Query) entityManager.createQuery(
                "select u from Role u where u.role = :role");
        q.setParameter("role", role);

        try {
            return (Role) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void addDBRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void update(User user) {

        entityManager.merge(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {


        return entityManager.createQuery("select u from User as u").getResultList();
    }

    @Override
    public void del(User user) {

        entityManager.remove(entityManager.contains(user)
                ? user
                : entityManager.merge(user));
    }
}

