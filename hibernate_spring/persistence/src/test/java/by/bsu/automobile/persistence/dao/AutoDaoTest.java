package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import by.bsu.automobile.persistence.entity.Auto;
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
 * Created by Sergey on 12.11.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class AutoDaoTest {
    @Autowired
    @Qualifier("autoDao")
    private EntityDAO autoDAO;

    @Test
    @Rollback
    public void createTest() {
        Auto auto = new Auto();
        auto.setMark("audi");
        auto.setModel("a4");
        auto.setSpecification("spec");
        auto.setYear(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        autoDAO.create(auto);

        Auto expectedAuto = (Auto) autoDAO.findById(auto.getId());

        Assert.assertEquals(auto, expectedAuto);
    }

    @Test
    public void findTest() {
        Assert.assertNotNull(autoDAO.findById(2));
    }

    @Test
    @Rollback
    public void updateTest() {
        Auto auto = (Auto) autoDAO.findById(2);

        auto.setMark("opel");
        autoDAO.edit(auto);

        Auto expectedAuto = (Auto) autoDAO.findById(auto.getId());

        Assert.assertEquals(auto, expectedAuto);
    }

    @Test
    public void deleteTest() {
        autoDAO.remove(3);

        Auto expectedAuto = (Auto) autoDAO.findById(3);

        Assert.assertNull(expectedAuto);
    }

    @Test
    public void findAllTest() {
        List<Auto> autos = (List<Auto>) autoDAO.findAll();

        Assert.assertFalse(autos.isEmpty());
    }
}
