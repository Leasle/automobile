package by.bsu.automobile.persistence.dao.impl;

import by.bsu.automobile.persistence.dao.AbstractDAO;
import by.bsu.automobile.persistence.dao.AutoDealerDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.AutoDealer;
import by.bsu.automobile.persistence.entity.AutoDealerPK;
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
    private static final String SELECT_BY_IDS = "select * from auto_dealer inner join dealer on id_Dealer = dealer.id inner join auto on id_Auto = auto.id where auto.id = :idAuto AND dealer.id = :idDealer";
    private static final String SELECT_ALL = "select * from auto_dealer";
    private static final String SELECT_BY_NAME = "select * from auto_dealer inner join dealer on id_Dealer = dealer.id where dealer.name = :nameDealer";
    private static final String SELECT_BY_MARK = "select * from auto_dealer inner join auto on id_Auto = auto.id where auto.mark = :markAuto";
    private static final String UPDATE_COST = "update auto_dealer set cost = :cost where id_Auto = :idAuto AND id_Dealer = :idDealer";
    private static final String SELECT_ALL_AUTODEALERS = "select * from auto_dealer inner join сart_auto_dealer on (id_Dealer, id_Auto) = (сart_auto_dealer.id_Dealer_Auto_Dealer, сart_auto_dealer.id_Auto_Auto_Dealer) inner join shopping_сart on id_Shopping_Сart = shopping_сart.id inner join user on id_User = user.id where user.id = :idUser";

    @Override
    public List<AutoDealer> findAllByNameDealer(String name) {
        Query query = getSession().createSQLQuery(SELECT_BY_NAME).addEntity(AutoDealer.class);

        return (List<AutoDealer>) query.list();
    }

    @Override
    public List<AutoDealer> findAllByYear(Date year) {
        return null;
    }

    @Override
    public List<AutoDealer> findAllByMark(String mark) {
        Query query = getSession().createSQLQuery(SELECT_BY_MARK).addEntity(AutoDealer.class);

        return (List<AutoDealer>) query.list();
    }

    @Override
    public List<AutoDealer> findAllByCostRange(double from, double to) {
        return null;
    }

    @Override
    public AutoDealer findByIds(int idAuto, int idDealer) {
        Query query = getSession().createSQLQuery(SELECT_BY_IDS).addEntity(AutoDealer.class).setParameter("idAuto", idAuto).setParameter("idDealer", idDealer);

        return (AutoDealer) query.uniqueResult();
    }

    @Override
    public AutoDealer findByPK(AutoDealerPK autoDealerPK) {
        Query query = getSession().createSQLQuery(SELECT_BY_IDS).addEntity(AutoDealer.class).setParameter("idAuto", autoDealerPK.getAuto().getId())
                .setParameter("idDealer", autoDealerPK.getDealer().getId());

        return (AutoDealer) query.uniqueResult();
    }

    @Override
    public void create(AutoDealer autoDealer) {
        persist(autoDealer);
    }

    @Override
    public void edit(AutoDealer autoDealer) {
//        Query query = getSession().createSQLQuery(UPDATE_COST).addEntity(AutoDealer.class).setParameter("idAuto", autoDealer.getAuto().getId())
//                .setParameter("idDealer", autoDealer.getDealer().getId()).setParameter("cost", autoDealer.getCost());
//
//        query.executeUpdate();
        update(autoDealer);
    }

    @Override
    public void remove(int idAuto, int idDealer) {
        AutoDealer autoDealer = findByIds(idAuto, idDealer);
        if (autoDealer != null) {
            delete(autoDealer);
        }
    }

    @Override
    public void remove(AutoDealerPK autoDealerPK) {
        AutoDealer autoDealer = findByPK(autoDealerPK);
        if (autoDealer != null) {
            delete(autoDealer);
        }
    }

    @Override
    public List<AutoDealer> findAll() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(AutoDealer.class);

        return (List<AutoDealer>) query.list();
    }

    @Override
    public List<AutoDealer> findAllAutoDealersOfUser(int idUser) {
        Query query = getSession().createSQLQuery(SELECT_ALL_AUTODEALERS).addEntity(AutoDealer.class).setParameter("idUser", idUser);

        return (List<AutoDealer>) query.list();
    }
}
