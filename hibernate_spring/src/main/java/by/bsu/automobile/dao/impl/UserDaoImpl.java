package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.EntityDAO;
import by.bsu.automobile.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDAO<User, Integer> implements EntityDAO<User> {
    public User findById(int id) {
        return getByKey(id);
    }

    public void create(User user) {
        persist(user);
    }

    public void edit(User user) {
        update(user);
    }

    public void remove(int id) {
        User user = getByKey(id);
        if (user != null) {
            delete(user);
        }
    }

    public List<User> findAll() {
        Criteria criteria = createEntityCriteria().setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        return (List<User>) criteria.list();
    }
}
