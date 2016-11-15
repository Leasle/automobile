package by.bsu.automobile.converters;

import by.bsu.automobile.dto.UserDataDTO;
import by.bsu.automobile.persistence.entity.UserData;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class UserDataDTOToUserDataConverter implements Converter<UserDataDTO, UserData> {
    @Override
    public UserData convert(UserDataDTO userDataDTO) {
        UserData userData = new UserData();
        userData.setFirstName(userDataDTO.getFirstName());
        userData.setLastName(userDataDTO.getLastName());
        return userData;
    }
}
