package by.bsu.automobile.service;

import by.bsu.automobile.entity.Dealer;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface DealerService {
    void addDealer(Dealer dealer);

    Dealer findById(int id);

    void updateDealer(Dealer dealer);

    void deleteDealerById(int id);

    List<Dealer> findAllDealers();
}
