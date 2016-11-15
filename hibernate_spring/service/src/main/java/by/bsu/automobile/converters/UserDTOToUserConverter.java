package by.bsu.automobile.converters;

import by.bsu.automobile.dto.UserDTO;
import by.bsu.automobile.persistence.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setRole(userDTO.getRole());
        return user;
    }
}
