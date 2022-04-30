package com.jrsofty.web.feeder.persistence.dao;

import java.util.List;

public interface GenericDAO<T> {

    T store(T instance);

    void delete(long id);

    T findById(long id);

    List<T> findAll();

}
