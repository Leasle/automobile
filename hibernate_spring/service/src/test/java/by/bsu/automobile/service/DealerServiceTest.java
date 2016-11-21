package by.bsu.automobile.service;

import by.bsu.automobile.dto.DealerDTO;
import by.bsu.automobile.persistence.dao.impl.DealerDaoImpl;
import by.bsu.automobile.persistence.entity.Dealer;
import by.bsu.automobile.service.impl.DealerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by Sergey on 14.11.2016.
 */


public class DealerServiceTest {
    @Mock
    private DealerDaoImpl dealerDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private DealerServiceImpl dealerService;

//    @Test
//    public void createDealerTest() {
//        DealerDTO dealer = new DealerDTO();
//        dealer.setName("dealer1");
//        dealer.setAddress("address1");
//
//        dealerService.addDealer(dealer);
//
////        Mockito.verify(dealerDao).create(dealer);
//    }

//    @Test
//    public void findDealerTest() {
//        Dealer dealer = new Dealer();
//        dealer.setId(1);
//        dealer.setName("dealer1");
//        dealer.setAddress("address1");
//
//        Mockito.when(dealerDao.findById(dealer.getId())).thenReturn(dealer);
//
//        dealerService.findDealerById(dealer.getId());
//
//        Mockito.verify(dealerDao).findById(dealer.getId());
//    }
}
