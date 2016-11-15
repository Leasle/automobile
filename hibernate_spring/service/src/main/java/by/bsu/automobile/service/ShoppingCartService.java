package by.bsu.automobile.service;

import by.bsu.automobile.dto.ShoppingCartDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface ShoppingCartService {
    ShoppingCartDTO addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO findShoppingCartById(int id);

    ShoppingCartDTO updateShoppingCart(int id, ShoppingCartDTO shoppingCartDTO);

    void deleteShoppingCartById(int id);

    List<ShoppingCartDTO> findAllShoppingCarts();

    List<ShoppingCartDTO> findAllShoppingCartsOfUser(int idUser);
}
