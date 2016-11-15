package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.AutoDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.Auto;
import by.bsu.automobile.service.AutoService;
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

@Service("autoService")
@Transactional
public class AutoServiceImpl implements AutoService {
    @Autowired
    @Qualifier("autoDao")
    private EntityDAO autoDao;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor autoDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Auto.class));
    private static final TypeDescriptor autoDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(AutoDTO.class));

    @Value("${auto.errors.notfound}")
    private String notFoundMessage;

    public AutoDTO addAuto(AutoDTO autoDTO) {
        Auto auto = conversionService.convert(autoDTO, Auto.class);
        autoDao.create(auto);

        return autoDTO;
    }

    public AutoDTO findAutoById(int id) {
        Auto auto = (Auto) autoDao.findById(id);

        if (auto == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(auto, AutoDTO.class);
    }

    public AutoDTO updateAuto(int id, AutoDTO autoDTO) {
        Auto auto = (Auto) autoDao.findById(id);

        if (auto == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        auto.setMark(autoDTO.getMark());
        auto.setModel(autoDTO.getModel());
        auto.setSpecification(autoDTO.getSpecification());
        auto.setYear(autoDTO.getYear());
        autoDao.edit(auto);

        return autoDTO;
    }

    public void deleteAutoById(int id) {
        autoDao.remove(id);
    }

    public List<AutoDTO> findAllAutos() {
        List<Auto> autos = (List<Auto>) autoDao.findAll();
        List<AutoDTO> autoDTOs = (List<AutoDTO>) conversionService.convert(autos, autoDescriptor, autoDTODescriptor);
        
        if (CollectionUtils.isEmpty(autoDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }
        
        return autoDTOs;
    }
}
