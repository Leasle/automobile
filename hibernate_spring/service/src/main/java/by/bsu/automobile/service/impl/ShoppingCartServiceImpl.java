package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.ShoppingCartDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.dao.ShoppingCartDAO;
import by.bsu.automobile.persistence.entity.ShoppingCart;
import by.bsu.automobile.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */

@Service("shoppingCartService")
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    @Qualifier("shoppingCartDao")
    private EntityDAO shoppingCartEntityDao;

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor shoppingCartDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ShoppingCart.class));
    private static final TypeDescriptor shoppingCartDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ShoppingCartDTO.class));

    @Value("${shoppingCart.errors.notfound}")
    private String notFoundMessage;

    public ShoppingCartDTO addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = conversionService.convert(shoppingCartDTO, ShoppingCart.class);
        shoppingCartEntityDao.create(shoppingCart);

        return shoppingCartDTO;
    }

    public ShoppingCartDTO findShoppingCartById(int id) {
        ShoppingCart shoppingCart = (ShoppingCart) shoppingCartEntityDao.findById(id);

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(shoppingCart, ShoppingCartDTO.class);
    }

    public ShoppingCartDTO updateShoppingCart(int id, ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = (ShoppingCart) shoppingCartEntityDao.findById(id);

        if (shoppingCart == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        shoppingCart.setDateTime(shoppingCartDTO.getDateTime());
        shoppingCartEntityDao.edit(shoppingCart);

        return shoppingCartDTO;
    }

    public void deleteShoppingCartById(int id) {
        shoppingCartEntityDao.remove(id);
    }

    public List<ShoppingCartDTO> findAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartEntityDao.findAll();
        List<ShoppingCartDTO> shoppingCartDTOs = (List<ShoppingCartDTO>) conversionService.convert(shoppingCarts, shoppingCartDescriptor, shoppingCartDTODescriptor);

        if (CollectionUtils.isEmpty(shoppingCartDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return shoppingCartDTOs;
    }

    public List<ShoppingCartDTO> findAllShoppingCartsOfUser(int idUser) {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartDAO.findUserCarts(idUser);
        List<ShoppingCartDTO> shoppingCartDTOs = (List<ShoppingCartDTO>) conversionService.convert(shoppingCarts, shoppingCartDescriptor, shoppingCartDTODescriptor);

        if (CollectionUtils.isEmpty(shoppingCartDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return shoppingCartDTOs;
    }
}
