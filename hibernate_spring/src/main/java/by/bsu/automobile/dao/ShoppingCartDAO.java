package by.bsu.automobile.dao;

import by.bsu.automobile.entity.ShoppingCart;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */
public interface ShoppingCartDAO {
    List<ShoppingCart> findUserCarts(int id);
}
