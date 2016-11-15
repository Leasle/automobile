package by.bsu.automobile.converters;

import by.bsu.automobile.dto.UserDataDTO;
import by.bsu.automobile.persistence.entity.UserData;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class UserDataToUserDataDTOConverter implements Converter<UserData, UserDataDTO> {
    @Override
    public UserDataDTO convert(UserData userData) {
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setFirstName(userData.getFirstName());
        userDataDTO.setLastName(userData.getLastName());
        return userDataDTO;
    }
}
