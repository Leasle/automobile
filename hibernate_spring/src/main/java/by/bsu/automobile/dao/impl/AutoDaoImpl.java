package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.EntityDAO;
import by.bsu.automobile.entity.Auto;
import by.bsu.automobile.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 24.10.2016.
 */

@Repository
@Transactional
public class AutoDaoImpl extends AbstractDAO<Auto, Integer> implements EntityDAO<Auto> {
    public Auto findById(int id) {
        return getByKey(id);
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
        Criteria criteria = createEntityCriteria().setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        return (List<Auto>) criteria.list();
    }
}
