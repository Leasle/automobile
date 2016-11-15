package by.bsu.automobile.converters;

import by.bsu.automobile.dto.ShoppingCartDTO;
import by.bsu.automobile.persistence.entity.ShoppingCart;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class ShoppingCartDTOToShoppingCartConverter implements Converter<ShoppingCartDTO, ShoppingCart> {
    @Override
    public ShoppingCart convert(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDateTime(shoppingCartDTO.getDateTime());
        return shoppingCart;
    }
}
