package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.UserDataDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.UserDataDAO;
import by.bsu.automobile.persistence.entity.UserData;
import by.bsu.automobile.service.UserDataService;
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

@Service("userDataService")
@Transactional
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    @Qualifier("userDataDao")
    private UserDataDAO userDataDao;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor userDataDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserData.class));
    private static final TypeDescriptor userDataDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDataDTO.class));

    @Value("${userData.errors.notfound}")
    private String notFoundMessage;

    public UserDataDTO addUserData(UserDataDTO userDataDTO) {
        UserData userData = conversionService.convert(userDataDTO, UserData.class);
        userDataDao.createUserData(userData);

        return userDataDTO;
    }

    public UserDataDTO findUserDataById(int id) {
        UserData userData = (UserData) userDataDao.findUserData(id);

        if (userData == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(userData, UserDataDTO.class);
    }

    public UserDataDTO updateUserData(int id, UserDataDTO userDataDTO) {
        UserData userData = (UserData) userDataDao.findUserData(id);

        if (userData == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        userData.setFirstName(userDataDTO.getFirstName());
        userData.setLastName(userDataDTO.getLastName());
        userDataDao.editUserData(userData);

        return userDataDTO;
    }

    public List<UserDataDTO> findAllUserDatas() {
        List<UserData> userDatas = (List<UserData>) userDataDao.allUserData();
        List<UserDataDTO> userDataDTOs = (List<UserDataDTO>) conversionService.convert(userDatas, userDataDescriptor, userDataDTODescriptor);

        if (CollectionUtils.isEmpty(userDataDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return userDataDTOs;
    }
}
