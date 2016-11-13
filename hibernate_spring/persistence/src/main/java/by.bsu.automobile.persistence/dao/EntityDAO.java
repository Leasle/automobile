package by.bsu.automobile.persistence.dao;

import java.util.List;

/**
 * Created by Sergey on 22.10.2016.
 */

public interface EntityDAO<T> {
    T findById(int id);

    void create(T entity);

    void edit(T entity);

    void remove(int id);

    List<T> findAll();
}
