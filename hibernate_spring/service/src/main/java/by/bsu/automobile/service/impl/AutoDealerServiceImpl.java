package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.AutoDealerDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.AutoDealerDAO;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.AutoDealer;
import by.bsu.automobile.service.AutoDealerService;
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

@Service("autoDealerService")
@Transactional
public class AutoDealerServiceImpl implements AutoDealerService {
    @Autowired
    private AutoDealerDAO autoDealerDAO;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor autoDealerDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(AutoDealer.class));
    private static final TypeDescriptor autoDealerDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(AutoDealerDTO.class));

    @Value("${autoAutoDealer.errors.notfound}")
    private String notFoundMessage;

    public AutoDealerDTO addAutoDealer(AutoDealerDTO autoDealerDTO) {
        AutoDealer autoAutoDealer = conversionService.convert(autoDealerDTO, AutoDealer.class);
        autoDealerDAO.create(autoAutoDealer);

        return autoDealerDTO;
    }

    public AutoDealerDTO findAutoDealerById(int idAuto, int idDealer) {
        AutoDealer autoAutoDealer = (AutoDealer) autoDealerDAO.findByIds(idAuto, idDealer);

        if (autoAutoDealer == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(autoAutoDealer, AutoDealerDTO.class);
    }

    public AutoDealerDTO updateAutoDealer(int idAuto, int idDealer, AutoDealerDTO autoDealerDTO) {
        AutoDealer autoAutoDealer = (AutoDealer) autoDealerDAO.findByIds(idAuto, idDealer);

        if (autoAutoDealer == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        autoAutoDealer.setCost(autoDealerDTO.getCost());
        autoDealerDAO.edit(autoAutoDealer);

        return autoDealerDTO;
    }

    public void deleteAutoDealerById(int idAuto, int idDealer) {
        autoDealerDAO.remove(idAuto, idDealer);
    }

    public List<AutoDealerDTO> findAllAutoDealers() {
        List<AutoDealer> autoAutoDealers = (List<AutoDealer>) autoDealerDAO.findAll();
        List<AutoDealerDTO> autoAutoDealerDTOs = (List<AutoDealerDTO>) conversionService.convert(autoAutoDealers, autoDealerDescriptor, autoDealerDTODescriptor);

        if (CollectionUtils.isEmpty(autoAutoDealerDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return autoAutoDealerDTOs;
    }
    
    public List<AutoDealerDTO> findAllAutoDealersOfUser(int idUser) {
        List<AutoDealer> autoAutoDealers = (List<AutoDealer>) autoDealerDAO.findAllAutoDealersOfUser(idUser);
        List<AutoDealerDTO> autoAutoDealerDTOs = (List<AutoDealerDTO>) conversionService.convert(autoAutoDealers, autoDealerDescriptor, autoDealerDTODescriptor);

        if (CollectionUtils.isEmpty(autoAutoDealerDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return autoAutoDealerDTOs;
    }
    
    public List<AutoDealerDTO> findAllAutoDealersByMark(String mark) {
        List<AutoDealer> autoAutoDealers = (List<AutoDealer>) autoDealerDAO.findAllByMark(mark);
        List<AutoDealerDTO> autoAutoDealerDTOs = (List<AutoDealerDTO>) conversionService.convert(autoAutoDealers, autoDealerDescriptor, autoDealerDTODescriptor);

        if (CollectionUtils.isEmpty(autoAutoDealerDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return autoAutoDealerDTOs;
    }
    
    public List<AutoDealerDTO> findAllAutoDealersByNameDealer(String dealerName) {
        List<AutoDealer> autoAutoDealers = (List<AutoDealer>) autoDealerDAO.findAllByNameDealer(dealerName);
        List<AutoDealerDTO> autoAutoDealerDTOs = (List<AutoDealerDTO>) conversionService.convert(autoAutoDealers, autoDealerDescriptor, autoDealerDTODescriptor);

        if (CollectionUtils.isEmpty(autoAutoDealerDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return autoAutoDealerDTOs;
    }   
}
