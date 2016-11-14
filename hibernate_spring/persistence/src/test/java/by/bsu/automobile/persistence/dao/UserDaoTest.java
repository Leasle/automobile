package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import by.bsu.automobile.persistence.entity.User;
import by.bsu.automobile.persistence.enums.ROLE;
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
public class UserDaoTest {
    @Autowired
    @Qualifier("userDao")
    private EntityDAO userDAO;

    @Test
    @Rollback
    public void createTest() {
        User user = new User();
        user.setLogin("login3");
        user.setPassword("pass123456789");
        user.setRole(ROLE.DEALER);

        userDAO.create(user);

        User expectedUser = (User) userDAO.findById(user.getId());

        Assert.assertEquals(user, expectedUser);
    }

    @Test
    public void findTest() {
        Assert.assertNotNull(userDAO.findById(2));
    }

    @Test
    @Rollback
    public void updateTest() {
        User user = (User) userDAO.findById(2);

        user.setLogin("login4");
        userDAO.edit(user);

        User expectedUser = (User) userDAO.findById(user.getId());

        Assert.assertEquals(user, expectedUser);
    }

    @Test
    public void deleteTest() {
        userDAO.remove(3);

        User expectedUser = (User) userDAO.findById(3);

        Assert.assertNull(expectedUser);
    }

    @Test
    public void findAllTest() {
        List<User> users = (List<User>) userDAO.findAll();

        Assert.assertFalse(users.isEmpty());
    }
}
