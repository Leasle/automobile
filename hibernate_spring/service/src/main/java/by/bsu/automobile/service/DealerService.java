package by.bsu.automobile.service;

import by.bsu.automobile.dto.DealerDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface DealerService {
    DealerDTO addDealer(DealerDTO dealerDTO);

    DealerDTO findDealerById(int id);

    DealerDTO updateDealer(int id, DealerDTO dealerDTO);

    void deleteDealerById(int id);

    List<DealerDTO> findAllDealers();
}
