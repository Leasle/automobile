package by.bsu.automobile.dao.impl;

import by.bsu.automobile.dao.AbstractDAO;
import by.bsu.automobile.dao.EntityDAO;
import by.bsu.automobile.dao.UserDataDAO;
import by.bsu.automobile.entity.User;
import by.bsu.automobile.entity.UserData;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sergey on 23.10.2016.
 */
@Repository("userDataDao")
@Transactional
public class UserDataDaoImpl extends AbstractDAO<UserData, Integer> implements UserDataDAO {
    public void createUserData(UserData userData) {
        persist(userData);
    }

    public UserData findUserData(int id) {
        UserData userData = getByKey(id);
        return userData;
    }

    public void editUserData(UserData userData) {
        update(userData);
    }

    public List<UserData> allUserData() {
        Criteria criteria = createEntityCriteria().setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<UserData> userDatas = (List<UserData>) criteria.list();

        return userDatas;
    }
}
