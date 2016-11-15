package by.bsu.automobile.converters;

import by.bsu.automobile.dto.UserDTO;
import by.bsu.automobile.persistence.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userDTO.getLogin());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
