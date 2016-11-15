package by.bsu.automobile.service;

import by.bsu.automobile.dto.AutoDealerDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface AutoDealerService {
    AutoDealerDTO addAutoDealer(AutoDealerDTO autoDealerDTO);

    AutoDealerDTO findAutoDealerById(int idAuto, int idDealer);

    AutoDealerDTO updateAutoDealer(int idAuto, int idDealer, AutoDealerDTO autoDealerDTO);

    void deleteAutoDealerById(int idAuto, int idDealer);

    List<AutoDealerDTO> findAllAutoDealers();

    List<AutoDealerDTO> findAllAutoDealersOfUser(int idUser);

    List<AutoDealerDTO> findAllAutoDealersByMark(String mark);

    List<AutoDealerDTO> findAllAutoDealersByNameDealer(String dealerNamer);
}
