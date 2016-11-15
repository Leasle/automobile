package by.bsu.automobile.converters;

import by.bsu.automobile.dto.DealerDTO;
import by.bsu.automobile.persistence.entity.Dealer;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Sergey on 15.11.2016.
 */
public class DealerDTOToDealerConverter implements Converter<DealerDTO, Dealer> {
    @Override
    public Dealer convert(DealerDTO dealerDTO) {
        Dealer dealer = new Dealer();
        dealer.setName(dealerDTO.getName());
        dealer.setAddress(dealerDTO.getAddress());
        return dealer;
    }
}
