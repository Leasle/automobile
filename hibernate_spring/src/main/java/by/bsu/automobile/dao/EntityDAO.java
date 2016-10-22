package by.bsu.automobile.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */
public interface EntityDAO<T> {
    T findById(int id);

    void create(T user);

    void edit(T user);

    void remove(int id);

    List<T> findAll();
}
