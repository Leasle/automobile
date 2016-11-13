package by.bsu.automobile.persistence.dao.impl;

import by.bsu.automobile.persistence.dao.AbstractDAO;
import by.bsu.automobile.persistence.dao.UserDataDAO;
import by.bsu.automobile.persistence.entity.UserData;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 23.10.2016.
 */

@Repository("userDataDao")
@Transactional
public class UserDataDaoImpl extends AbstractDAO<UserData, Integer> implements UserDataDAO {
    private static final String SELECT_BY_ID = "select * from user_data where id_User = :idEntity";
    private static final String SELECT_ALL = "select * from user_data";

    public void createUserData(UserData userData) {
        persist(userData);
    }

    public UserData findUserData(int id) {
        Query query = getSession().createSQLQuery(SELECT_BY_ID).addEntity(UserData.class).setParameter("idEntity", id);

        return (UserData) query.uniqueResult();
    }

    public void editUserData(UserData userData) {
        update(userData);
    }

    public List<UserData> allUserData() {
        Query query = getSession().createSQLQuery(SELECT_ALL).addEntity(UserData.class);

        return (List<UserData>) query.list();
    }
}
