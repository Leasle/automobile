package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.EntityDAO;
import by.bsu.automobile.entity.Auto;
import by.bsu.automobile.entity.Dealer;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 24.10.2016.
 */

@Transactional
@Repository("dealerDao")
public class DealerDaoImpl extends AbstractDAO<Dealer, Integer>  implements EntityDAO<Dealer> {
    public Dealer findById(int id) {
        return getByKey(id);
    }

    public void create(Dealer dealer) {
        persist(dealer);
    }

    public void edit(Dealer dealer) {
        update(dealer);
    }

    public void remove(int id) {
        Dealer dealer = getByKey(id);
        if (dealer != null) {
            delete(dealer);
        }
    }

    public List<Dealer> findAll() {
        Criteria criteria = createEntityCriteria();
//                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        return (List<Dealer>) criteria.list();
    }
}
