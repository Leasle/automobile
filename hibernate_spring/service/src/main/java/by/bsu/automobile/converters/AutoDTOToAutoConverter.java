package by.bsu.automobile.converters;

import by.bsu.automobile.dto.AutoDTO;
import by.bsu.automobile.persistence.entity.Auto;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class AutoDTOToAutoConverter implements Converter<AutoDTO, Auto> {
    @Override
    public Auto convert(AutoDTO autoDTO) {
        Auto auto = new Auto();
        auto.setModel(autoDTO.getModel());
        auto.setMark(autoDTO.getMark());
        auto.setSpecification(autoDTO.getSpecification());
        auto.setYear(autoDTO.getYear());
        return auto;
    }
}
