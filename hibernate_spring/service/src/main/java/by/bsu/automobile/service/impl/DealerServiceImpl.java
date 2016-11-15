package by.bsu.automobile.service.impl;

import by.bsu.automobile.dto.DealerDTO;
import by.bsu.automobile.exceptions.ResourceNotFoundException;
import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.Dealer;
import by.bsu.automobile.service.DealerService;
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

@Service("dealerService")
@Transactional
public class DealerServiceImpl implements DealerService {
    @Autowired
    @Qualifier("dealerDao")
    private EntityDAO dealerDao;

    @Autowired
    private ConversionService conversionService;

    private static final TypeDescriptor dealerDescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Dealer.class));
    private static final TypeDescriptor dealerDTODescriptor =
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(DealerDTO.class));

    @Value("${dealer.errors.notfound}")
    private String notFoundMessage;

    public DealerDTO addDealer(DealerDTO dealerDTO) {
        Dealer dealer = conversionService.convert(dealerDTO, Dealer.class);
        dealerDao.create(dealer);

        return dealerDTO;
    }

    public DealerDTO findDealerById(int id) {
        Dealer dealer = (Dealer) dealerDao.findById(id);

        if (dealer == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return conversionService.convert(dealer, DealerDTO.class);
    }

    public DealerDTO updateDealer(int id, DealerDTO dealerDTO) {
        Dealer dealer = (Dealer) dealerDao.findById(id);

        if (dealer == null) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        dealer.setName(dealerDTO.getName());
        dealer.setAddress(dealerDTO.getAddress());
        dealerDao.edit(dealer);

        return dealerDTO;
    }

    public void deleteDealerById(int id) {
        dealerDao.remove(id);
    }

    public List<DealerDTO> findAllDealers() {
        List<Dealer> dealers = (List<Dealer>) dealerDao.findAll();
        List<DealerDTO> dealerDTOs = (List<DealerDTO>) conversionService.convert(dealers, dealerDescriptor, dealerDTODescriptor);

        if (CollectionUtils.isEmpty(dealerDTOs)) {
            throw new ResourceNotFoundException(notFoundMessage);
        }

        return dealerDTOs;
    }
}
