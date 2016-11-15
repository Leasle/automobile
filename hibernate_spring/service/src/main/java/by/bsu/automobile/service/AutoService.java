package by.bsu.automobile.service;

import by.bsu.automobile.dto.AutoDTO;

import java.util.List;

/**
 * Created by Sergey on 29.10.2016.
 */
public interface AutoService {
    AutoDTO addAuto(AutoDTO autoDTO);

    AutoDTO findAutoById(int id);

    AutoDTO updateAuto(int id, AutoDTO autoDTO);

    void deleteAutoById(int id);

    List<AutoDTO> findAllAutos();
}
