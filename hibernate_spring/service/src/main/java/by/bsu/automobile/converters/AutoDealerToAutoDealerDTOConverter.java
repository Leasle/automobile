package by.bsu.automobile.converters;

import by.bsu.automobile.dto.AutoDealerDTO;
import by.bsu.automobile.persistence.entity.AutoDealer;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class AutoDealerToAutoDealerDTOConverter implements Converter<AutoDealer, AutoDealerDTO> {
    @Override
    public AutoDealerDTO convert(AutoDealer autoDealer) {
        AutoDealerDTO autoDealerDTO = new AutoDealerDTO();
        autoDealerDTO.setCost(autoDealer.getCost());
        return autoDealerDTO;
    }
}
