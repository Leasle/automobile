package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.entity.ShoppingCart;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */
public interface ShoppingCartDAO {
    List<ShoppingCart> findUserCarts(int id);
}
