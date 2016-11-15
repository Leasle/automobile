package by.bsu.automobile.converters;

import by.bsu.automobile.dto.AutoDTO;
import by.bsu.automobile.persistence.entity.Auto;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class AutoToAutoDTOConverter implements Converter<Auto, AutoDTO> {
    @Override
    public AutoDTO convert(Auto auto) {
        AutoDTO autoDTO = new AutoDTO();
        autoDTO.setModel(auto.getModel());
        autoDTO.setMark(auto.getMark());
        autoDTO.setSpecification(auto.getSpecification());
        autoDTO.setYear(auto.getYear());
        return autoDTO;
    }
}
