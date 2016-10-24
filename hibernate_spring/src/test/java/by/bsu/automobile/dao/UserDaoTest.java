package by.bsu.automobile.dao;

import by.bsu.automobile.dao.impl.UserDaoImpl;
import by.bsu.automobile.entity.Auto;
import by.bsu.automobile.entity.Dealer;
import by.bsu.automobile.entity.User;
import by.bsu.automobile.entity.UserData;
import by.bsu.automobile.enums.ROLE;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import by.bsu.automobile.configuration.HibernateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 23.10.2016.
 */

@ContextConfiguration(classes = HibernateConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
    @Qualifier("dealerDao")
    private EntityDAO dealerEntityDAO;

//    @Test
//    @Transactional
////    @Rollback(true)
//    public void testUser() {
//        User user = new User();
//        user.setLogin("abcd");
//        user.setPassword("12345678");
//        user.setRole(ROLE.CLIENT);
//
////
////        UserData userData = new UserData();
////        userData.setFirstName("Vasya");
////        userData.setLastName("Petrov");
////        userData.setUser(user);
////
////        user.setUserData(userData);
//        entityDAO.create(user);
//
//        System.out.println(user.toString());
//
//        List<User> users = (List<User>) entityDAO.findAll();
//        System.out.println(users.toString());
////        Assert.assertEquals(user.getLogin(), users.get(0).getLogin());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testAuto() {
//        Auto auto = new Auto();
//        auto.setMark("audi");
//        auto.setModel("a4");
////        auto.setYear();
////        entityDAO.create(user);
//
////        System.out.println(user.toString());
//
////        List<User> users = (List<User>) entityDAO.findAll();
////        System.out.println(users.toString());
////        Assert.assertEquals(user.getLogin(), users.get(0).getLogin());
//    }

    @Test
    @Transactional
    @Rollback(true)
    public void createDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer1");
        dealer.setAddress("Sunday str.");

        dealerEntityDAO.create(dealer);

        System.out.println(dealer.getId());
        System.out.println(dealerEntityDAO.findById(dealer.getId()).toString());

        List<Dealer> dealers = dealerEntityDAO.findAll();
        System.out.println(dealers.toString());
    }
}
