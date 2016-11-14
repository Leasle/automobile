package by.bsu.automobile.persistence.dao.impl;

import by.bsu.automobile.persistence.dao.AbstractDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.dao.ShoppingCartDAO;
import by.bsu.automobile.persistence.entity.ShoppingCart;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 14.11.2016.
 */

@Repository("shoppingCartDao")
@Transactional
public class ShoppingCartDaoImpl extends AbstractDAO<ShoppingCart, Integer> implements EntityDAO<ShoppingCart>, ShoppingCartDAO {
    private static final String SELECT_BY_ID = "select * from shopping_сart where id = :idEntity";
    private static final String SELECT_BY_USER_ID = "select * from shopping_сart where id_User = :idUser";
    private static final String SELECT_ALL = "select * from shopping_сart";

    public ShoppingCart findById(int id) {
        Query query = getSession().createSQLQuery(SELECT_BY_ID).addEntity(ShoppingCart.class).setParameter("idEntity", id);

        return (ShoppingCart) query.uniqueResult();
    }

    public void create(ShoppingCart shoppingCart) {
        persist(shoppingCart);
    }

    public void edit(ShoppingCart shoppingCart) {
        update(shoppingCart);
    }

    public void remove(int id) {
        ShoppingCart shoppingCart = findById(id);
        if (shoppingCart != null) {
            delete(shoppingCart);
        }
    }

    public List<ShoppingCart> findAll() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(ShoppingCart.class);

        return (List<ShoppingCart>) query.list();
    }

    @Override
    public List<ShoppingCart> findUserCarts(int idUser) {
        Query query = getSession().createSQLQuery(SELECT_BY_USER_ID).addEntity(ShoppingCart.class).setParameter("idUser", idUser);

        return (List<ShoppingCart>) query.list();
    }
}
