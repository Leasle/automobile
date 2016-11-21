package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import by.bsu.automobile.persistence.entity.ShoppingCart;
import by.bsu.automobile.persistence.entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sergey on 14.11.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class ShoppingCartDaoTest {
    @Autowired
    @Qualifier("shoppingCartDao")
    private EntityDAO shoppingCartEntityDAO;

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

//    @Test
//    @Rollback
//    public void createTest() {
//        User user = new User();
//        user.setId(4);
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setUser(user);
//        shoppingCart.setDateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//
//        shoppingCartEntityDAO.create(shoppingCart);
//
//        ShoppingCart expectedShoppingCart = (ShoppingCart) shoppingCartEntityDAO.findById(shoppingCart.getId());
//
//        System.out.println(shoppingCart.getDateTime().equals(expectedShoppingCart.getDateTime()));
//        System.out.println(shoppingCart.getDateTime().getTime());
//        System.out.println(expectedShoppingCart.getDateTime().getTime());
//
//        Assert.assertTrue(shoppingCart.getDateTime().equals(expectedShoppingCart.getDateTime())
//                && shoppingCart.getUser().getId() == expectedShoppingCart.getUser().getId());
//    }

    @Test
    public void findTest() {
        Assert.assertNotNull(shoppingCartEntityDAO.findById(2));
    }

//    @Test
//    @Rollback
//    public void updateTest() {
//        ShoppingCart shoppingCart = (ShoppingCart) shoppingCartEntityDAO.findById(2);
//
//        shoppingCart.setDateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis() - 3000));
//        shoppingCartEntityDAO.edit(shoppingCart);
//
//        ShoppingCart expectedShoppingCart = (ShoppingCart) shoppingCartEntityDAO.findById(shoppingCart.getId());
//
//        Assert.assertEquals(shoppingCart, expectedShoppingCart);
//    }

    @Test
    public void deleteTest() {
        shoppingCartEntityDAO.remove(3);

        ShoppingCart expectedShoppingCart = (ShoppingCart) shoppingCartEntityDAO.findById(3);

        Assert.assertNull(expectedShoppingCart);
    }

    @Test
    public void findAllTest() {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartEntityDAO.findAll();

        Assert.assertFalse(shoppingCarts.isEmpty());
    }

    @Test
    public void findAllUserTest() {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartDAO.findUserCarts(4);

        System.out.println(shoppingCarts.toString());

        Assert.assertFalse(shoppingCarts.isEmpty());
    }
}
