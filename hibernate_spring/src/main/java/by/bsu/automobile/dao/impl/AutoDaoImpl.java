package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.EntityDAO;
import by.bsu.automobile.entity.Auto;
import by.bsu.automobile.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 24.10.2016.
 */

@Repository("autoDao")
@Transactional
public class AutoDaoImpl extends AbstractDAO<Auto, Integer> implements EntityDAO<Auto> {
    private static final String SELECT_BY_ID = "select * from auto where id = :idEntity";
    private static final String SELECT_ALL = "select * from auto";

    public Auto findById(int id) {
        Query query = getSession().createSQLQuery(SELECT_BY_ID).addEntity(Auto.class).setParameter("idEntity", id);

        return (Auto) query.uniqueResult();
    }

    public void create(Auto auto) {
        persist(auto);
    }

    public void edit(Auto auto) {
        update(auto);
    }

    public void remove(int id) {
        Auto auto = getByKey(id);
        if (auto != null) {
            delete(auto);
        }
    }

    public List<Auto> findAll() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(Auto.class);

        return (List<Auto>) query.list();
    }
}
