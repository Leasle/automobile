package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.AutoDealerDAO;
import by.bsu.automobile.entity.AutoDealer;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */

@Transactional
@Repository("autoDealerDao")
public class AutoDealerDaoImpl extends AbstractDAO<AutoDealer, Integer> implements AutoDealerDAO {
    private static final String SELECT_BY_NAME = "select * from auto_dealer inner join dealer on id_Dealer = dealer.id where dealer.name = :nameDealer";
    private static final String SELECT_BY_MARK = "select * from auto_dealer inner join auto on id_Auto = auto.id where auto.mark = :markAuto";

    public List<AutoDealer> findAllByNameDealer(String name) {
        Query query = getSession().createSQLQuery(SELECT_BY_NAME).addEntity(AutoDealer.class);

        return (List<AutoDealer>) query.list();
    }

    public List<AutoDealer> findAllByYear(Date year) {
        return null;
    }

    public List<AutoDealer> findAllByMark(String mark) {
        Query query = getSession().createSQLQuery(SELECT_BY_MARK).addEntity(AutoDealer.class);

        return (List<AutoDealer>) query.list();
    }

    public List<AutoDealer> findAllByCostRange(double from, double to) {
        return null;
    }
}
