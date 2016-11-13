package by.bsu.automobile.persistence.dao.impl;

import by.bsu.automobile.persistence.dao.AbstractDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.Dealer;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 24.10.2016.
 */

@Transactional
@Repository("dealerDao")
public class DealerDaoImpl extends AbstractDAO<Dealer, Integer>  implements EntityDAO<Dealer> {
    private static final String SELECT_BY_ID = "select * from dealer where id = :idEntity";
    private static final String SELECT_ALL = "select * from dealer";

    public Dealer findById(int id) {
        Query query = getSession().createSQLQuery(SELECT_BY_ID).addEntity(Dealer.class).setParameter("idEntity", id);

        return (Dealer) query.uniqueResult();
    }

    public void create(Dealer dealer) {
        persist(dealer);
    }

    public void edit(Dealer dealer) {
        update(dealer);
    }

    public void remove(int id) {
        Dealer dealer = findById(id);
        if (dealer != null) {
            delete(dealer);
        }
    }

    public List<Dealer> findAll() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(Dealer.class);

        return (List<Dealer>) query.list();
    }
}
