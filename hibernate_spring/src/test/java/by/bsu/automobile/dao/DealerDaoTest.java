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
public class DealerDaoTest {

    @Autowired
    @Qualifier("dealerDao")
    private EntityDAO dealerEntityDAO;

    @Test
    @Transactional
    @Rollback
    public void createDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer2");
        dealer.setAddress("Sunday str.2");

        dealerEntityDAO.create(dealer);

        Dealer dealer1 = (Dealer) dealerEntityDAO.findById(dealer.getId());

        Assert.assertEquals(dealer, dealer1);
    }

    @Test
    @Transactional
    @Rollback
    public void updateDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer2");
        dealer.setAddress("Sunday str.2");

        dealerEntityDAO.create(dealer);

        dealer.setName("dealer3");
        dealerEntityDAO.edit(dealer);

        Dealer dealer1 = (Dealer) dealerEntityDAO.findById(dealer.getId());

        Assert.assertEquals(dealer, dealer1);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer2");
        dealer.setAddress("Sunday str.2");

        dealerEntityDAO.create(dealer);

        dealerEntityDAO.remove(dealer.getId());

        Dealer dealer1 = (Dealer) dealerEntityDAO.findById(dealer.getId());

        Assert.assertNull(dealer1);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer2");
        dealer.setAddress("Sunday str.2");

        dealerEntityDAO.create(dealer);

        List<Dealer> dealers = (List<Dealer>) dealerEntityDAO.findAll();

        Assert.assertTrue(dealers.size() > 0);
    }
}
