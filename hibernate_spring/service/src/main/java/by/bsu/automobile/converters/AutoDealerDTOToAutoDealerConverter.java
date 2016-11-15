package by.bsu.automobile.converters;

import by.bsu.automobile.dto.AutoDealerDTO;
import by.bsu.automobile.persistence.entity.AutoDealer;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class AutoDealerDTOToAutoDealerConverter implements Converter<AutoDealerDTO, AutoDealer> {
    @Override
    public AutoDealer convert(AutoDealerDTO autoDealerDTO) {
        AutoDealer autoDealer = new AutoDealer();
        autoDealer.setCost(autoDealerDTO.getCost());
        return autoDealer;
    }
}
