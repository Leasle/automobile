package by.bsu.automobile.service.impl;

import by.bsu.automobile.persistence.dao.EntityDAO;
import by.bsu.automobile.persistence.entity.Dealer;
import by.bsu.automobile.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void addDealer(Dealer dealer) {
        dealerDao.create(dealer);
    }

    public Dealer findById(int id) {
        return (Dealer) dealerDao.findById(id);
    }

    public void updateDealer(Dealer dealer) {
        dealerDao.edit(dealer);
    }

    public void deleteDealerById(int id) {
        dealerDao.remove(id);
    }

    public List<Dealer> findAllDealers() {
        return (List<Dealer>) dealerDao.findAll();
    }
}
