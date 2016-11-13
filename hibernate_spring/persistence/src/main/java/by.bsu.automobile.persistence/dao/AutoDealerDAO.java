package by.bsu.automobile.persistence.dao;

import by.bsu.automobile.persistence.entity.AutoDealer;

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
}
