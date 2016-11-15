package by.bsu.automobile.converters;

import by.bsu.automobile.dto.ShoppingCartDTO;
import by.bsu.automobile.persistence.entity.ShoppingCart;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class ShoppingCartToShoppingCartDTOConverter implements Converter<ShoppingCart, ShoppingCartDTO> {
    @Override
    public ShoppingCartDTO convert(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setDateTime(shoppingCart.getDateTime());
        return shoppingCartDTO;
    }
}
