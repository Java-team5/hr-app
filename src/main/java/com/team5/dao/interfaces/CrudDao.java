package com.team5.dao.interfaces;

import java.util.List;

public interface CrudDao<T> {

    //create
    void save(T model);

    //read
    T getById(long id);
    List<T> getAll();
    int count();


    //update
    void update(T model);

    //delete
    void delete(long id);
}
