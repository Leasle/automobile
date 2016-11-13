package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.entity.UserData;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */
public interface UserDataDAO {
    void createUserData(UserData userData);

    UserData findUserData(int id);

    void editUserData(UserData userData);

    List<UserData> allUserData();
}
