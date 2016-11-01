package by.bsu.automobile.dao;

import by.bsu.automobile.entity.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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
