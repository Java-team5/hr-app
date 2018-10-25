package team5.dao;

import java.util.List;

public interface CrudDao<T> {
    T findByID(long id);
    void save(T model);
    void update(T model);
    void delete(long id);
    List<T> findAll();
}
