package by.bsu.automobile.service;

import by.bsu.automobile.dto.UserDataDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface UserDataService {
    UserDataDTO addUserData(UserDataDTO userDataDTO);

    UserDataDTO findUserDataById(int id);

    UserDataDTO updateUserData(int id, UserDataDTO userDataDTO);

    List<UserDataDTO> findAllUserDatas();
}
