package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.UserDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.User;
import by.bsu.automobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private EntityDAO userDao;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor userDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(User.class));
    private static final TypeDescriptor userDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDTO.class));

    @Value("${user.errors.notfound}")
    private String notFoundMessage;

    public UserDTO addUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        userDao.create(user);

        return userDTO;
    }

    public UserDTO findUserById(int id) {
        User user = (User) userDao.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(user, UserDTO.class);
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user = (User) userDao.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        user.setLogin(userDTO.getLogin());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        userDao.edit(user);

        return userDTO;
    }

    public void deleteUserById(int id) {
        userDao.remove(id);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        List<UserDTO> userDTOs = (List<UserDTO>) conversionService.convert(users, userDescriptor, userDTODescriptor);

        if (CollectionUtils.isEmpty(userDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return userDTOs;
    }
}
