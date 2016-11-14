package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.entity.AutoDealer;
import by.bsu.automobile.persistence.entity.AutoDealerPK;

import java.sql.Date;
import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */
public interface AutoDealerDAO {
    List<AutoDealer> findAllByNameDealer(String name);

    List<AutoDealer> findAllByYear(Date year);

    List<AutoDealer> findAllByMark(String mark);

    List<AutoDealer> findAllByCostRange(double from, double to);

    AutoDealer findByIds(int idAuto, int idDealer);

    AutoDealer findByPK(AutoDealerPK autoDealerPK);

    void create(AutoDealer autoDealer);

    void edit(AutoDealer autoDealer);

    void remove(int idAuto, int idDealer);

    void remove(AutoDealerPK autoDealerPK);

    List<AutoDealer> findAll();

    List<AutoDealer> findAllAutoDealersOfUser(int idUser);
}
