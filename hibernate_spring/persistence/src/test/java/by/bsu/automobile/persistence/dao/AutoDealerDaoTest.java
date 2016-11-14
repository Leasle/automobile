package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import by.bsu.automobile.persistence.dao.impl.AutoDealerDaoImpl;
import by.bsu.automobile.persistence.entity.Auto;
import by.bsu.automobile.persistence.entity.AutoDealer;
import by.bsu.automobile.persistence.dao.AutoDealerDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.AutoDealerPK;
import by.bsu.automobile.persistence.entity.Dealer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hibernate.Query;
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
 * Created by Sergey on 13.11.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class AutoDealerDaoTest {

    @Autowired
    @Qualifier("autoDealerDao")
    private AutoDealerDAO autoDealerDAO;


    @Test
    @Rollback
    public void createTest() {
        Auto auto = new Auto();
        auto.setId(2);

        Dealer dealer = new Dealer();
        dealer.setId(1);

        AutoDealerPK autoDealerPK = new AutoDealerPK(auto, dealer);

        AutoDealer autoDealer = new AutoDealer();
        autoDealer.setAutoDealerPK(autoDealerPK);
        autoDealer.setCost(1000);

        autoDealerDAO.create(autoDealer);

        AutoDealer expectedAutoDealer = (AutoDealer) autoDealerDAO.findByPK(autoDealerPK);

        Assert.assertTrue(expectedAutoDealer.getAuto().getId() == autoDealer.getAuto().getId()
                && expectedAutoDealer.getDealer().getId() == autoDealer.getDealer().getId()
                && expectedAutoDealer.getCost() == 1000);
    }

    @Test
    public void findTest() {
        Assert.assertNotNull(autoDealerDAO.findByIds(2, 2));
    }

    @Test
    @Rollback
    public void updateTest() {
        AutoDealer autoDealer = (AutoDealer) autoDealerDAO.findByIds(1, 2);

        autoDealer.setCost(1500);
        autoDealerDAO.edit(autoDealer);

        AutoDealer expectedAutoDealer = (AutoDealer) autoDealerDAO.findByPK(autoDealer.getAutoDealerPK());

        Assert.assertTrue(expectedAutoDealer.getAuto().getId() == autoDealer.getAuto().getId()
                && expectedAutoDealer.getDealer().getId() == autoDealer.getDealer().getId()
                && expectedAutoDealer.getCost() == 1500);
    }

    @Test
    public void deleteTest() {
        autoDealerDAO.remove(2, 2);

        AutoDealer expectedAutoDealer = (AutoDealer) autoDealerDAO.findByIds(2, 2);

        Assert.assertNull(expectedAutoDealer);
    }

    @Test
    public void findAllTest() {
        List<AutoDealer> autoDealers = (List<AutoDealer>) autoDealerDAO.findAll();

        Assert.assertFalse(autoDealers.isEmpty());
    }

    @Test
    public void findAllAutoDealersOfUser() {
        List<AutoDealer> autoDealers = (List<AutoDealer>) autoDealerDAO.findAllAutoDealersOfUser(1);

        Assert.assertFalse(autoDealers.isEmpty());
    }
}
