package by.bsu.automobile.converters;

import by.bsu.automobile.dto.DealerDTO;
import by.bsu.automobile.persistence.entity.Dealer;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class DealerToDealerDTOConverter implements Converter<Dealer, DealerDTO> {
    @Override
    public DealerDTO convert(Dealer dealer) {
        DealerDTO dealerDTO = new DealerDTO();
        dealerDTO.setName(dealer.getName());
        dealerDTO.setAddress(dealer.getAddress());
        return dealerDTO;
    }
}
