package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.configuration.HibernateConfiguration;
import by.bsu.automobile.persistence.entity.User;
import by.bsu.automobile.persistence.entity.UserData;
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
public class UserDataDaoTest {
    @Autowired
    @Qualifier("userDataDao")
    private UserDataDAO userDataDAO;

    @Test
    @Rollback
    public void createTest() {
        User user = new User();
        user.setId(4);

        UserData userData = new UserData();
        userData.setFirstName("firstName1");
        userData.setLastName("lastName1");
        userData.setUser(user);

        userDataDAO.createUserData(userData);

        UserData expectedUserData = (UserData) userDataDAO.findUserData(userData.getUser().getId());

        Assert.assertTrue(userData.getFirstName().equals(expectedUserData.getFirstName())
                && userData.getLastName().equals(expectedUserData.getLastName()));
    }

    @Test
    public void findTest() {
        Assert.assertNotNull(userDataDAO.findUserData(2));
    }

    @Test
    @Rollback
    public void updateTest() {
        UserData userData = (UserData) userDataDAO.findUserData(2);

        userData.setFirstName("firstName2");
        userDataDAO.editUserData(userData);

        UserData expectedUserData = (UserData) userDataDAO.findUserData(userData.getId());

        Assert.assertTrue(userData.getFirstName().equals(expectedUserData.getFirstName())
                && userData.getLastName().equals(expectedUserData.getLastName()));
    }

    @Test
    public void findAllTest() {
        List<UserData> userDatas = (List<UserData>) userDataDAO.allUserData();

        Assert.assertFalse(userDatas.isEmpty());
    }
}
