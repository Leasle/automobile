package by.bsu.automobile.persistence.dao.impl;

import by.bsu.automobile.persistence.dao.AbstractDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDAO<User, Integer> implements EntityDAO<User> {
    private static final String SELECT_BY_ID = "select * from user where id = :idEntity";
    private static final String SELECT_ALL = "select * from user";

    public User findById(int id) {
        Query query = getSession().createSQLQuery(SELECT_BY_ID).addEntity(User.class).setParameter("idEntity", id);

        return (User) query.uniqueResult();
    }

    public void create(User user) {
        persist(user);
    }

    public void edit(User user) {
        update(user);
    }

    public void remove(int id) {
        User user = findById(id);
        if (user != null) {
            delete(user);
        }
    }

    public List<User> findAll() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(User.class);

        return (List<User>) query.list();
    }
}
