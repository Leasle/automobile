package by.bsu.automobile.service;

import by.bsu.automobile.dto.UserDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface UserService {
    UserDTO addUser(UserDTO userDTO);

    UserDTO findUserById(int id);

    UserDTO updateUser(int id, UserDTO userDTO);

    void deleteUserById(int id);

    List<UserDTO> findAllUsers();
}
