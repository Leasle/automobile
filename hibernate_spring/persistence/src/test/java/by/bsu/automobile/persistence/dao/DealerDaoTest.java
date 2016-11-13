package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.entity.Dealer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

/**
 * Created by Sergey on 23.10.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class DealerDaoTest {

    @Autowired
    @Qualifier("dealerDao")
    private EntityDAO dealerEntityDAO;

    @Test
    @Rollback
    public void createTest() {
        Dealer dealer = new Dealer();
        dealer.setName("dealer2");
        dealer.setAddress("Sunday str.2");

        dealerEntityDAO.create(dealer);

        Dealer dealer1 = (Dealer) dealerEntityDAO.findById(dealer.getId());

        Assert.assertEquals(dealer, dealer1);
    }

    @Test
    public void findTest() {
        Assert.assertNotNull(dealerEntityDAO.findById(2));
    }

    @Test
    @Rollback
    public void updateTest() {
        Dealer dealer = (Dealer) dealerEntityDAO.findById(2);

        dealer.setName("dealer4");
        dealerEntityDAO.edit(dealer);

        Dealer expectedDealer = (Dealer) dealerEntityDAO.findById(dealer.getId());

        Assert.assertEquals(dealer, expectedDealer);
    }

    @Test
    public void deleteTest() {
        dealerEntityDAO.remove(3);

        Dealer expectedDealer = (Dealer) dealerEntityDAO.findById(3);

        Assert.assertNull(expectedDealer);
    }

    @Test
    public void findAllTest() {
        List<Dealer> dealers = (List<Dealer>) dealerEntityDAO.findAll();

        Assert.assertFalse(dealers.isEmpty());
    }
}
